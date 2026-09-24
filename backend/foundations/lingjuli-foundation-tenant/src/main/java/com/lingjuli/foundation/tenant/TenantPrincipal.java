package com.lingjuli.foundation.tenant;

public record TenantPrincipal(long tenantId, long userId, Long employeeId, Long organizationId, Long storeId) {}
