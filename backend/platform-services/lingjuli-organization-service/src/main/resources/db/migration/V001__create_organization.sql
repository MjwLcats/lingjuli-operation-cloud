CREATE TABLE organization_unit (
    id BIGINT NOT NULL COMMENT '组织单元ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    parent_id BIGINT NULL COMMENT '上级组织单元ID',
    org_code VARCHAR(64) NOT NULL COMMENT '组织编码',
    org_name VARCHAR(128) NOT NULL COMMENT '组织名称',
    org_type VARCHAR(32) NOT NULL COMMENT '组织类型',
    hierarchy_path VARCHAR(1024) NOT NULL COMMENT '组织层级路径',
    hierarchy_level INT NOT NULL COMMENT '组织层级',
    sort_order INT NOT NULL DEFAULT 0 COMMENT '排序序号',
    org_status VARCHAR(32) NOT NULL COMMENT '组织状态',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_organization_unit_tenant_code (tenant_id, org_code),
    KEY idx_organization_unit_tenant_parent (tenant_id, parent_id),
    KEY idx_organization_unit_tenant_path (tenant_id, hierarchy_path(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织单元';

CREATE TABLE position (
    id BIGINT NOT NULL COMMENT '岗位ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    position_code VARCHAR(64) NOT NULL COMMENT '岗位编码',
    position_name VARCHAR(128) NOT NULL COMMENT '岗位名称',
    position_status VARCHAR(32) NOT NULL COMMENT '岗位状态',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_position_tenant_code (tenant_id, position_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

CREATE TABLE employee (
    id BIGINT NOT NULL COMMENT '员工ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    employee_no VARCHAR(64) NOT NULL COMMENT '员工编号',
    employee_name VARCHAR(128) NOT NULL COMMENT '员工姓名',
    mobile VARCHAR(32) NULL COMMENT '手机号码',
    employment_status VARCHAR(32) NOT NULL COMMENT '任职状态',
    user_id BIGINT NULL COMMENT '关联登录账号ID',
    hired_on DATE NULL COMMENT '入职日期',
    left_on DATE NULL COMMENT '离职日期',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_by BIGINT NULL COMMENT '更新人',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记',
    version BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁版本',
    PRIMARY KEY (id),
    UNIQUE KEY uk_employee_tenant_no (tenant_id, employee_no),
    KEY idx_employee_tenant_user (tenant_id, user_id),
    KEY idx_employee_tenant_status (tenant_id, employment_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工档案';

CREATE TABLE employee_assignment (
    id BIGINT NOT NULL COMMENT '员工任职关系ID',
    tenant_id BIGINT NOT NULL COMMENT '租户ID',
    employee_id BIGINT NOT NULL COMMENT '员工ID',
    organization_id BIGINT NOT NULL COMMENT '组织单元ID',
    position_id BIGINT NULL COMMENT '岗位ID',
    store_id BIGINT NULL COMMENT '门店ID',
    primary_assignment TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否主任职关系',
    effective_from DATE NOT NULL COMMENT '生效日期',
    effective_until DATE NULL COMMENT '失效日期',
    created_by BIGINT NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_employee_assignment_tenant_employee (tenant_id, employee_id),
    KEY idx_employee_assignment_tenant_org (tenant_id, organization_id),
    KEY idx_employee_assignment_tenant_store (tenant_id, store_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工任职关系';
