# Flyway 规范

每服务独立执行 Flyway，Migration 位于 `src/main/resources/db/migration`，版本在服务内唯一。已发布 Migration 不得修改、删除或重排。

本地演示数据不能进入正式 Migration。CI 必须验证空库迁移；生产修复使用前向 Migration。
