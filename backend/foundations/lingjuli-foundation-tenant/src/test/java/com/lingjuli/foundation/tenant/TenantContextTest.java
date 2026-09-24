package com.lingjuli.foundation.tenant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TenantContextTest {
    @AfterEach
    void clearContext() {
        TenantContext.clear();
    }

    @Test
    void returnsCurrentTenantPrincipal() {
        TenantContext.set(new TenantPrincipal(10L, 20L, null, null, 30L));
        assertEquals(10L, TenantContext.require().tenantId());
    }

    @Test
    void rejectsMissingTenantContext() {
        assertThrows(IllegalStateException.class, TenantContext::require);
    }
}
