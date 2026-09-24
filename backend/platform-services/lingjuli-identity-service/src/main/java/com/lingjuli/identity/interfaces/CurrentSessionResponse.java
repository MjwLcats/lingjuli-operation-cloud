package com.lingjuli.identity.interfaces;

import com.lingjuli.identity.domain.LoginSession;
import java.time.Instant;

public record CurrentSessionResponse(String userId, String tenantId, String accountType, Instant expiresAt) {
    static CurrentSessionResponse from(LoginSession session) {
        return new CurrentSessionResponse(Long.toString(session.userId()),
            session.tenantId() == null ? null : Long.toString(session.tenantId()), session.accountType().name(), session.expiresAt());
    }
}
