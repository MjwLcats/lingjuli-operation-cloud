# 测试策略

- Domain：纯单元测试。
- Application：用例与权限测试。
- Repository：MySQL 集成测试，重点覆盖双租户隔离。
- API：契约、Validation 和错误映射测试。
- Event：Outbox 与幂等消费测试。
- Frontend：类型检查、组件测试和关键业务逻辑测试。
