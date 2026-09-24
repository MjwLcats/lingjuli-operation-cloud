# API 规则

产品、内部与开放 API 分别使用 `/api/v1`、`/internal/v1`、`/openapi/v1`。资源路径使用小写复数名词；发布、审批等业务动作允许使用动作子资源。

响应统一包含 code、message、data、requestId。协议错误使用正确 HTTP 状态码；业务错误使用分域错误码。分页统一使用 page、pageSize、items、total。
