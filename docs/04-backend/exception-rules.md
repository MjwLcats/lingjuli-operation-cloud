# 异常规范

异常分为 Domain、Application、Infrastructure 与 API 映射。业务异常必须有稳定错误码；未知异常统一映射系统错误并记录 traceId，不向客户端暴露堆栈、SQL 或内部地址。

错误码域包括 SYS、AUTH、TENANT、ORG、STORE、SALES、DISH、WORKFORCE、ATTENDANCE、INSPECTION、AI、INTEGRATION。
