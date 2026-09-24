package com.lingjuli.identity.domain;

public record LoginAccount(
    long userId,
    Long tenantId,
    String loginName,
    String displayName,
    String passwordHash,
    String status,
    AccountType accountType
) {
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }
}
