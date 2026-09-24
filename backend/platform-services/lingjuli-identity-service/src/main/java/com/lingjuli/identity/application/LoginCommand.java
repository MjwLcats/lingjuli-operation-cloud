package com.lingjuli.identity.application;

import com.lingjuli.identity.domain.AccountType;

public record LoginCommand(
    AccountType accountType,
    Long tenantId,
    String loginName,
    String password,
    String clientIp,
    String userAgent
) {}
