# 依赖规则

- 服务间禁止代码级依赖对方实现模块，禁止共享 Entity 和 Repository。
- 同步调用使用 `/internal/v1/**` 契约；第三方使用 `/openapi/v1/**`。
- 异步协作发布版本化领域事件，消费者必须幂等。
- 服务内部依赖方向为 `interfaces → application → domain`，`infrastructure` 实现 Domain 端口。
- Dashboard、Reporting、AI 不得绕过业务服务访问其数据库。
