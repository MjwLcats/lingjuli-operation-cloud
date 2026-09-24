package com.lingjuli.identity.application;

import com.lingjuli.foundation.core.error.BusinessException;
import com.lingjuli.identity.domain.AccountType;
import com.lingjuli.identity.domain.AuthErrorCode;
import com.lingjuli.identity.domain.LoginAccount;
import com.lingjuli.identity.domain.LoginSession;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private static final Duration SESSION_DURATION = Duration.ofHours(8);
    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final Clock clock;

    public LoginService(AccountRepository accountRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this(accountRepository, sessionRepository, passwordEncoder, new TokenService(), Clock.systemUTC());
    }

    LoginService(AccountRepository accountRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder,
                 TokenService tokenService, Clock clock) {
        this.accountRepository = accountRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.clock = clock;
    }

    @Transactional
    public LoginResult login(LoginCommand command) {
        LoginAccount account = findAccount(command)
            .orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_CREDENTIALS));
        if (!passwordEncoder.matches(command.password(), account.passwordHash())) {
            throw new BusinessException(AuthErrorCode.INVALID_CREDENTIALS);
        }
        if (!account.isActive()) {
            throw new BusinessException(AuthErrorCode.ACCOUNT_DISABLED);
        }

        Instant issuedAt = clock.instant();
        Instant expiresAt = issuedAt.plus(SESSION_DURATION);
        String accessToken = tokenService.generate();
        LoginSession session = new LoginSession(UUID.randomUUID().toString(), account.accountType(), account.userId(), account.tenantId(), expiresAt);
        sessionRepository.create(session, account, tokenService.hash(accessToken), command.clientIp(), command.userAgent(), issuedAt);
        accountRepository.recordSuccessfulLogin(account, issuedAt);
        return new LoginResult(accessToken, expiresAt, account.userId(), account.tenantId(), account.displayName(), account.accountType());
    }

    public LoginSession requireSession(String accessToken) {
        return sessionRepository.findActive(tokenService.hash(accessToken), clock.instant())
            .orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_SESSION));
    }

    @Transactional
    public void logout(String accessToken) {
        sessionRepository.revoke(tokenService.hash(accessToken), clock.instant());
    }

    private Optional<LoginAccount> findAccount(LoginCommand command) {
        if (command.accountType() == AccountType.PLATFORM) {
            return accountRepository.findPlatformAccount(command.loginName());
        }
        if (command.tenantId() == null) {
            return Optional.empty();
        }
        return accountRepository.findMerchantAccount(command.tenantId(), command.loginName());
    }
}
