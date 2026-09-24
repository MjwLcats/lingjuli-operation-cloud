package com.lingjuli.identity.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lingjuli.foundation.core.error.BusinessException;
import com.lingjuli.identity.domain.AccountType;
import com.lingjuli.identity.domain.AuthErrorCode;
import com.lingjuli.identity.domain.LoginAccount;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    private static final Instant NOW = Instant.parse("2026-09-24T08:00:00Z");
    @Mock private AccountRepository accountRepository;
    @Mock private SessionRepository sessionRepository;
    private LoginService loginService;
    private String passwordHash;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        passwordHash = passwordEncoder.encode("correct-password");
        loginService = new LoginService(accountRepository, sessionRepository, passwordEncoder,
            new TokenService(), Clock.fixed(NOW, ZoneOffset.UTC));
    }

    @Test
    void merchantLoginScopesAccountLookupToRequestedTenant() {
        LoginAccount account = new LoginAccount(7L, 101L, "manager", "manager", passwordHash, "ACTIVE", AccountType.MERCHANT);
        when(accountRepository.findMerchantAccount(101L, "manager")).thenReturn(Optional.of(account));

        LoginResult result = loginService.login(new LoginCommand(AccountType.MERCHANT, 101L, "manager",
            "correct-password", "127.0.0.1", "test"));

        assertEquals(101L, result.tenantId());
        assertEquals(AccountType.MERCHANT, result.accountType());
        verify(accountRepository).findMerchantAccount(101L, "manager");
        verify(accountRepository, never()).findPlatformAccount(any());
        verify(sessionRepository).create(any(), eq(account), any(), eq("127.0.0.1"), eq("test"), eq(NOW));
    }

    @Test
    void platformLoginNeverUsesTenantScopedAccount() {
        LoginAccount account = new LoginAccount(9L, null, "operator", "平台运营员", passwordHash, "ACTIVE", AccountType.PLATFORM);
        when(accountRepository.findPlatformAccount("operator")).thenReturn(Optional.of(account));

        LoginResult result = loginService.login(new LoginCommand(AccountType.PLATFORM, 999L, "operator",
            "correct-password", "127.0.0.1", "test"));

        assertEquals(AccountType.PLATFORM, result.accountType());
        assertEquals(null, result.tenantId());
        verify(accountRepository, never()).findMerchantAccount(any(Long.class), any());
    }

    @Test
    void returnsSamePublicErrorForUnknownAccountAndWrongPassword() {
        when(accountRepository.findMerchantAccount(101L, "manager")).thenReturn(Optional.empty());
        BusinessException unknown = assertThrows(BusinessException.class, () -> loginService.login(
            new LoginCommand(AccountType.MERCHANT, 101L, "manager", "wrong", null, null)));

        LoginAccount account = new LoginAccount(7L, 101L, "manager", "manager", passwordHash, "ACTIVE", AccountType.MERCHANT);
        when(accountRepository.findMerchantAccount(101L, "manager")).thenReturn(Optional.of(account));
        BusinessException wrongPassword = assertThrows(BusinessException.class, () -> loginService.login(
            new LoginCommand(AccountType.MERCHANT, 101L, "manager", "wrong", null, null)));

        assertEquals(AuthErrorCode.INVALID_CREDENTIALS, unknown.getErrorCode());
        assertEquals(unknown.getErrorCode(), wrongPassword.getErrorCode());
    }

    @Test
    void rejectsDisabledAccountWithoutCreatingSession() {
        LoginAccount account = new LoginAccount(7L, 101L, "manager", "manager", passwordHash, "DISABLED", AccountType.MERCHANT);
        when(accountRepository.findMerchantAccount(101L, "manager")).thenReturn(Optional.of(account));

        BusinessException exception = assertThrows(BusinessException.class, () -> loginService.login(
            new LoginCommand(AccountType.MERCHANT, 101L, "manager", "correct-password", null, null)));

        assertEquals(AuthErrorCode.ACCOUNT_DISABLED, exception.getErrorCode());
        verify(sessionRepository, never()).create(any(), any(), any(), any(), any(), any());
    }

    @Test
    void tokenServiceGeneratesUniqueTokensAndStableHashes() {
        TokenService tokenService = new TokenService();
        String first = tokenService.generate();
        String second = tokenService.generate();

        assertNotEquals(first, second);
        assertEquals(tokenService.hash(first), tokenService.hash(first));
        assertNotEquals(first, tokenService.hash(first));
    }
}
