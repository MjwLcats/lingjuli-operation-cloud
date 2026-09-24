package com.lingjuli.identity.application;

import com.lingjuli.identity.domain.LoginAccount;
import com.lingjuli.identity.domain.LoginSession;
import java.time.Instant;
import java.util.Optional;

public interface SessionRepository {
    void create(LoginSession session, LoginAccount account, String tokenHash, String clientIp, String userAgent, Instant issuedAt);
    Optional<LoginSession> findActive(String tokenHash, Instant now);
    void revoke(String tokenHash, Instant revokedAt);
}
