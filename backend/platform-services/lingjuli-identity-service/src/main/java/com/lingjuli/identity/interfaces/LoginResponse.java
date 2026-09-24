package com.lingjuli.identity.interfaces;

import com.lingjuli.identity.application.LoginResult;
import java.time.Instant;

public record LoginResponse(
    String accessToken,
    Instant expiresAt,
    String userId,
    String tenantId,
    String displayName,
    String accountType
) {
    static LoginResponse from(LoginResult result) {
        return new LoginResponse(result.accessToken(), result.expiresAt(), Long.toString(result.userId()),
            result.tenantId() == null ? null : Long.toString(result.tenantId()), result.displayName(), result.accountType().name());
    }
}
