package com.lingjuli.identity.infrastructure;

import com.lingjuli.identity.application.SessionRepository;
import com.lingjuli.identity.domain.AccountType;
import com.lingjuli.identity.domain.LoginAccount;
import com.lingjuli.identity.domain.LoginSession;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public JdbcSessionRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(LoginSession session, LoginAccount account, String tokenHash, String clientIp, String userAgent, Instant issuedAt) {
        String sql = """
            INSERT INTO login_session
                (id, account_type, user_id, tenant_id, token_hash, issued_at, expires_at, client_ip, user_agent)
            VALUES
                (:id, :accountType, :userId, :tenantId, :tokenHash, :issuedAt, :expiresAt, :clientIp, :userAgent)
            """;
        Map<String, Object> values = new HashMap<>();
        values.put("id", session.sessionId());
        values.put("accountType", account.accountType().name());
        values.put("userId", account.userId());
        values.put("tenantId", account.tenantId());
        values.put("tokenHash", tokenHash);
        values.put("issuedAt", Timestamp.from(issuedAt));
        values.put("expiresAt", Timestamp.from(session.expiresAt()));
        values.put("clientIp", clientIp);
        values.put("userAgent", userAgent);
        jdbc.update(sql, values);
    }

    @Override
    public Optional<LoginSession> findActive(String tokenHash, Instant now) {
        String sql = """
            SELECT id, account_type, user_id, tenant_id, expires_at
            FROM login_session
            WHERE token_hash = :tokenHash AND revoked_at IS NULL AND expires_at > :now
            """;
        return jdbc.query(sql, Map.of("tokenHash", tokenHash, "now", Timestamp.from(now)), (rs, rowNum) ->
            new LoginSession(rs.getString("id"), AccountType.valueOf(rs.getString("account_type")),
                rs.getLong("user_id"), rs.getObject("tenant_id", Long.class), rs.getTimestamp("expires_at").toInstant())
        ).stream().findFirst();
    }

    @Override
    public void revoke(String tokenHash, Instant revokedAt) {
        jdbc.update("UPDATE login_session SET revoked_at = :revokedAt WHERE token_hash = :tokenHash AND revoked_at IS NULL",
            Map.of("revokedAt", Timestamp.from(revokedAt), "tokenHash", tokenHash));
    }
}
