# API 集成

组件不得直接使用 Axios。调用链固定为 Component → Hook/Service → API Client。统一处理认证头、Tenant 工作上下文、Request ID、超时、错误映射与取消。

OpenAPI 用于生成客户端类型；生成代码不可手工修改。产品、内部和 Open API 契约必须分离。
