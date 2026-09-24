# AI 架构

AI、Prediction、Knowledge 分别建模。AI 服务负责模型 Provider、Agent、Prompt 和工具编排；Prediction 负责统计/机器学习执行；Knowledge 负责文档、Embedding 和检索。

LLM 不得直接连接业务数据库或执行未经授权的写操作。事实数据通过受权限保护的工具 API 获取。
