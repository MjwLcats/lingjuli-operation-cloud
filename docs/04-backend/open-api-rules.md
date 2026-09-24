# Open API 规则

外部 API 不复用内部 Controller。客户端凭据绑定 Tenant、Scope、配额和数据范围；支持 OAuth 2.0 Client Credentials、签名、防重放、限流、幂等与审计。

公开版本拥有独立兼容和弃用周期，破坏性变更必须发布新主版本。
