# Flyway 规范

每服务独立执行 Flyway，Migration 位于 `src/main/resources/db/migration`，版本在服务内唯一。已发布 Migration 不得修改、删除或重排。

本地演示数据不能进入正式 Migration。CI 必须验证空库迁移；生产修复使用前向 Migration。

## Bootstrap 与 Migration 边界

- `database/bootstrap/V001__create_platform_databases.sql` 只负责创建首批逻辑数据库，不创建业务表，也不替代 Flyway。
- 业务表、索引、约束、正式初始化数据必须由数据所属服务的 Migration 管理。
- Bootstrap 的版本号与各服务 Migration 的版本号属于不同序列，不能混用或相互推断。

## 当前基线

tenant、identity、authorization、organization、store 五个服务的 `V001` 已在目标 MySQL 实例成功执行，并写入各自的 `flyway_schema_history`。这些文件已经成为不可变更的数据库基线：任何后续结构调整必须新增 `V002__*.sql`，不得编辑、删除、重命名或重新排序现有 `V001`。

尚未建立数据库的服务，应在启用该服务时先创建自己的 `lingjuli_<domain>` 数据库，再从该服务的 `V001` 开始迁移；不得提前把所有规划数据库一次性创建出来。

## 验证要求

- Migration 提交前必须验证从空库可完整执行。
- 升级 Migration 必须验证从当前生产基线向前执行。
- 禁止依赖 `baselineOnMigrate` 掩盖未知结构，禁止运行时自动生成 DDL。
- 生产执行前必须备份并记录执行人、目标环境、版本和结果。
