package com.lingjuli.identity.infrastructure;

import com.lingjuli.identity.application.AccountRepository;
import com.lingjuli.identity.domain.AccountType;
import com.lingjuli.identity.domain.LoginAccount;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAccountRepository implements AccountRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public JdbcAccountRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<LoginAccount> findPlatformAccount(String loginName) {
        String sql = """
            SELECT id, login_name, display_name, password_hash, account_status
            FROM platform_account
            WHERE login_name = :loginName AND deleted = 0
            """;
        return jdbc.query(sql, Map.of("loginName", loginName), (rs, rowNum) -> platformAccount(rs)).stream().findFirst();
    }

    @Override
    public Optional<LoginAccount> findMerchantAccount(long tenantId, String loginName) {
        String sql = """
            SELECT id, tenant_id, login_name, password_hash, account_status
            FROM user_account
            WHERE tenant_id = :tenantId AND login_name = :loginName AND deleted = 0
            """;
        return jdbc.query(sql, Map.of("tenantId", tenantId, "loginName", loginName),
            (rs, rowNum) -> merchantAccount(rs)).stream().findFirst();
    }

    @Override
    public void recordSuccessfulLogin(LoginAccount account, Instant loginAt) {
        String table = account.accountType() == AccountType.PLATFORM ? "platform_account" : "user_account";
        String tenantClause = account.accountType() == AccountType.MERCHANT ? " AND tenant_id = :tenantId" : "";
        Map<String, Object> values = new java.util.HashMap<>();
        values.put("id", account.userId());
        values.put("loginAt", Timestamp.from(loginAt));
        if (account.tenantId() != null) {
            values.put("tenantId", account.tenantId());
        }
        jdbc.update("UPDATE " + table + " SET last_login_at = :loginAt WHERE id = :id" + tenantClause,
            values);
    }

    private LoginAccount platformAccount(ResultSet rs) throws SQLException {
        return new LoginAccount(rs.getLong("id"), null, rs.getString("login_name"), rs.getString("display_name"),
            rs.getString("password_hash"), rs.getString("account_status"), AccountType.PLATFORM);
    }

    private LoginAccount merchantAccount(ResultSet rs) throws SQLException {
        String loginName = rs.getString("login_name");
        return new LoginAccount(rs.getLong("id"), rs.getLong("tenant_id"), loginName, loginName,
            rs.getString("password_hash"), rs.getString("account_status"), AccountType.MERCHANT);
    }
}
