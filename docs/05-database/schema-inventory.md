# Schema 清单

本文件记录已经实际创建并验证的数据库基线，不等同于未来服务规划。更新时间：2026-09-24。

## 已创建数据库

| 数据库 | 所属服务 | 业务表 | 当前版本 |
|---|---|---:|---|
| `lingjuli_tenant` | `lingjuli-tenant-service` | `tenant` | V001 |
| `lingjuli_identity` | `lingjuli-identity-service` | `user_account` | V001 |
| `lingjuli_authorization` | `lingjuli-authorization-service` | `role`, `permission_resource`, `role_permission`, `user_role`, `role_data_scope` | V001 |
| `lingjuli_organization` | `lingjuli-organization-service` | `organization_unit`, `position`, `employee`, `employee_assignment` | V001 |
| `lingjuli_store` | `lingjuli-store-service` | `store` | V001 |

合计 5 个逻辑数据库、12 张业务表。每个数据库另有自己的 `flyway_schema_history`，该表不计入业务表数量。

## 数据所有权

- tenant 管理 SaaS 租户生命周期，不代表集团、区域或门店。
- identity 管理账号身份；authorization 管理角色、权限资源和数据范围，两者禁止合并为万能用户库。
- organization 管理租户内组织树、岗位、员工和任职关系。
- store 管理门店经营实体及其组织归属，不拥有组织树。
- 服务不得访问其他服务数据库；跨域数据通过 API 或事件获得。

## 尚未创建

sales、dish、workforce、attendance、inspection、dashboard、reporting、prediction、ai、knowledge、notification、file、scheduler、integration 等服务虽然已有工程骨架或目标数据库名，但当前不属于已初始化数据库。启用前必须先完成领域模型评审、Migration、权限设计和测试，再按需建库。

## 维护规则

每次数据库发生实际变更时，同一提交必须更新本清单中的数据库、表和版本信息。清单不能替代 Flyway 文件，也不能作为执行 SQL 的来源。
