package com.lingjuli.identity.application;

import com.lingjuli.identity.domain.LoginAccount;
import java.time.Instant;
import java.util.Optional;

public interface AccountRepository {
    Optional<LoginAccount> findPlatformAccount(String loginName);
    Optional<LoginAccount> findMerchantAccount(long tenantId, String loginName);
    void recordSuccessfulLogin(LoginAccount account, Instant loginAt);
}
