package com.lingjuli.identity.application;

import com.lingjuli.identity.domain.AccountType;
import java.time.Instant;

public record LoginResult(
    String accessToken,
    Instant expiresAt,
    long userId,
    Long tenantId,
    String displayName,
    AccountType accountType
) {}
