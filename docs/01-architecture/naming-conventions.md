# 命名规范

- Maven Group：`com.lingjuli`
- 服务 Artifact/Nacos 名：`lingjuli-<domain>-service`
- Java 包：`com.lingjuli.<domain>`
- npm 包：`@lingjuli/<name>`
- 数据库：`lingjuli_<domain>`
- Redis：`lingjuli:{environment}:{tenantId}:{service}:{resource}:{identifier}`
- 产品 API：`/api/v1/**`
- 内部 API：`/internal/v1/**`
- 开放 API：`/openapi/v1/**`

目录与 URL 使用 kebab-case，Java 类型使用 PascalCase，变量使用 camelCase，数据库对象使用 snake_case。
