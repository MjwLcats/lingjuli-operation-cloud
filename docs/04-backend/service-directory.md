# 服务目录规范

服务名称必须表达稳定能力。启动类位于根包，REST 入口位于 `interfaces/rest`，用例位于 `application/command|query`，领域模型位于 `domain`，JDBC 和外部 Client 位于 `infrastructure`。

禁止建立 `operation-service`、`system-service` 或其他无法判断数据所有权的模块。
