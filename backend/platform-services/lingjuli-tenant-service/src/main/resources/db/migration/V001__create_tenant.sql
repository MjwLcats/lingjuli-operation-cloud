CREATE TABLE tenant (
    id BIGINT NOT NULL COMMENT '租户ID',
    tenant_code VARCHAR(64) NOT NULL COMMENT '租户编码',
    tenant_name VARCHAR(128) NOT NULL COMMENT '租户名称',
    status VARCHAR(32) NOT NULL COMMENT '租户状态',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_tenant_tenant_code (tenant_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SaaS租户';
