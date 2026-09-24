package com.lingjuli.foundation.tenant;

import java.util.Objects;
import java.util.Optional;

public final class TenantContext {
    private static final ThreadLocal<TenantPrincipal> CURRENT = new ThreadLocal<>();

    private TenantContext() {}

    public static void set(TenantPrincipal principal) {
        CURRENT.set(Objects.requireNonNull(principal, "principal"));
    }

    public static TenantPrincipal require() {
        return Optional.ofNullable(CURRENT.get())
            .orElseThrow(() -> new IllegalStateException("tenant context is required"));
    }

    public static void clear() {
        CURRENT.remove();
    }
}
