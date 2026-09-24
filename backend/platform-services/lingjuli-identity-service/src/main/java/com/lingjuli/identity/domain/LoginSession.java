package com.lingjuli.identity.domain;

import java.time.Instant;

public record LoginSession(
    String sessionId,
    AccountType accountType,
    long userId,
    Long tenantId,
    Instant expiresAt
) {}
