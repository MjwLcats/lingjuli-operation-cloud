# 仓库结构

仓库采用统一 Monorepo：`apps` 放客户端应用，`packages` 放有明确消费者的共享包，`backend` 放 Maven 多服务工程，`docs` 放长期有效规范，`database` 放跨服务数据库约定，`tools` 放工程工具，`deploy` 放部署描述。

共享包不能承载应用专属页面或业务流程。后端 Foundation 不能承载具体领域模型。
