# 仓库结构

仓库采用统一 Monorepo：`apps` 放客户端应用，`packages` 放有明确消费者的共享包，`backend` 放 Maven 多服务工程，`docs` 放长期有效规范，`database` 放跨服务数据库约定，`tools` 放工程工具，`deploy` 放部署描述。

共享包不能承载应用专属页面或业务流程。后端 Foundation 不能承载具体领域模型。

## 一级目录

| 目录 | 职责 |
|---|---|
| `apps/lingjuli-web` | 单一 Web 应用，按平台端/商家端路由与权限域隔离，可独立构建部署 |
| `apps/lingjuli-mobile` | uni-app x 移动执行端，独立构建发布，不受 Web 部署影响 |
| `packages/*` | Design Token、Web UI、API Client 等有明确消费者的共享包 |
| `backend/gateways` | 内部 API Gateway 与 Open Gateway |
| `backend/platform-services` | 租户、身份、授权、组织、门店等平台服务 |
| `backend/business-services` | 销售、菜品、用工、考勤、巡检等领域服务 |
| `backend/query-services` | 工作台与报表只读模型服务 |
| `backend/intelligence-services` | 预测、LLM/Agent、知识库服务 |
| `backend/capability-services` | 通知、文件、调度等独立能力服务 |
| `backend/integration-services` | 外部系统适配与同步 |
| `backend/foundations` | 稳定技术能力，不包含具体业务模型 |
| `database` | 实例级 Bootstrap 与跨服务数据库约定 |
| `docs` | 架构、设计系统、研发和业务规范 |
