CREATE TABLE role (
    id BIGINT NOT NULL COMMENT '角色ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    role_code VARCHAR(64) NOT NULL COMMENT '角色编码',
    role_name VARCHAR(128) NOT NULL COMMENT '角色名称',
    role_status VARCHAR(32) NOT NULL COMMENT '角色状态',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_tenant_code (tenant_id, role_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户角色';

CREATE TABLE permission_resource (
    id BIGINT NOT NULL COMMENT '权限资源ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    permission_code VARCHAR(128) NOT NULL COMMENT '权限编码',
    permission_name VARCHAR(128) NOT NULL COMMENT '权限名称',
    resource_type VARCHAR(32) NOT NULL COMMENT '资源类型',
    permission_status VARCHAR(32) NOT NULL COMMENT '权限状态',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_permission_tenant_code (tenant_id, permission_code),
    KEY idx_permission_tenant_type (tenant_id, resource_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源';

CREATE TABLE role_permission (
    id BIGINT NOT NULL COMMENT '角色权限关系ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    permission_id BIGINT NOT NULL COMMENT '权限资源ID',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_permission_tenant_role_permission (tenant_id, role_id, permission_id),
    KEY idx_role_permission_tenant_permission (tenant_id, permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系';

CREATE TABLE user_role (
    id BIGINT NOT NULL COMMENT '用户角色关系ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    user_id BIGINT NOT NULL COMMENT '账号ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    valid_from DATETIME(3) NULL COMMENT '生效时间',
    valid_until DATETIME(3) NULL COMMENT '失效时间',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_role_tenant_user_role (tenant_id, user_id, role_id),
    KEY idx_user_role_tenant_role (tenant_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系';

CREATE TABLE role_data_scope (
    id BIGINT NOT NULL COMMENT '角色数据范围ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    scope_type VARCHAR(32) NOT NULL COMMENT '数据范围类型',
    target_type VARCHAR(32) NULL COMMENT '指定对象类型',
    target_id BIGINT NULL COMMENT '指定对象ID',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_role_data_scope_tenant_role (tenant_id, role_id),
    KEY idx_role_data_scope_tenant_target (tenant_id, target_type, target_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色数据范围';
