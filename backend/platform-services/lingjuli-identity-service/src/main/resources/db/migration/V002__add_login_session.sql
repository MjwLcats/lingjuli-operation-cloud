CREATE TABLE platform_account (
    id BIGINT NOT NULL COMMENT '平台账号ID',
    login_name VARCHAR(64) NOT NULL COMMENT '登录名',
    display_name VARCHAR(128) NOT NULL COMMENT '显示名称',
    password_hash VARCHAR(255) NOT NULL COMMENT '密码哈希',
    account_status VARCHAR(32) NOT NULL COMMENT '账号状态',
    last_login_at DATETIME(3) NULL COMMENT '最后登录时间',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_platform_account_login_name (login_name),
    KEY idx_platform_account_status (account_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SaaS平台账号';

CREATE TABLE login_session (
    id CHAR(36) NOT NULL COMMENT '登录会话ID',
    account_type VARCHAR(32) NOT NULL COMMENT '账号类型',
    user_id BIGINT NOT NULL COMMENT '账号ID',
    tenant_id BIGINT NULL COMMENT '租户ID，平台账号为空',
    token_hash CHAR(64) NOT NULL COMMENT '访问令牌SHA-256摘要',
    issued_at DATETIME(3) NOT NULL COMMENT '签发时间',
    expires_at DATETIME(3) NOT NULL COMMENT '过期时间',
    revoked_at DATETIME(3) NULL COMMENT '吊销时间',
    client_ip VARCHAR(64) NULL COMMENT '登录客户端IP',
    user_agent VARCHAR(512) NULL COMMENT '登录客户端标识',
    PRIMARY KEY (id),
    UNIQUE KEY uk_login_session_token_hash (token_hash),
    KEY idx_login_session_account (account_type, user_id),
    KEY idx_login_session_tenant_user (tenant_id, user_id),
    KEY idx_login_session_expires_at (expires_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录会话';
