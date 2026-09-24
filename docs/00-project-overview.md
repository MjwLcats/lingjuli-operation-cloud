# 项目总览

同庆云是餐饮行业多租户 SaaS 运营平台。平台覆盖 SaaS 平台治理、集团/区域/门店管理、销售、菜品、用工、考勤、巡检、工作台、报表、AI、预测与知识库。

## 端与使用者

- 平台管理端：SaaS 运营人员，允许经过授权和审计的跨租户治理。
- 商家管理端：集团、区域和门店管理人员，限定当前租户和数据范围。
- 移动端：门店与员工执行工具，聚焦待办、采集、提醒和业务处理。
- Open API：第三方客户与合作系统，使用独立身份、Scope、配额和版本策略。

## 架构原则

- 统一 Monorepo，各应用与服务独立构建部署。
- 后端采用按领域预拆的 Spring Cloud 服务，禁止按菜单拆服务。
- 每服务拥有自己的逻辑数据库、Flyway 历史和数据所有权。
- Tenant、Organization、Region、Store 分别建模。
- RBAC 与 Data Scope 共同构成授权。
- MySQL 是事实源；Milvus 是可重建的向量检索副本。
- Design System 先于业务页面建设。

## 当前实现状态

项目地基已完成第一轮初始化：

- Monorepo、Maven 多模块服务骨架与统一命名规范已经建立。
- Web 当前采用单一 `apps/lingjuli-web` 应用，通过顶层路由和权限域隔离平台端与商家端；移动端位于 `apps/lingjuli-mobile`。
- 首批 tenant、identity、authorization、organization、store 服务已经拥有独立 Flyway V001。
- 远程 MySQL 实例已创建首批 5 个逻辑数据库并执行 V001，共建立 12 张业务表；其他服务数据库尚未初始化。
- 当前仅完成项目基础能力和骨架，不代表销售、菜品、用工、考勤、巡检等业务功能已经实现。
- 服务专用数据库运行账号、最小权限授权及生产级凭据托管仍属于后续基础设施工作。

当前数据库实况以 [Schema 清单](05-database/schema-inventory.md) 为准，初始化方式见 [数据库初始化](05-database/database-initialization.md)。

参见：[总体架构](01-architecture/architecture.md)、[服务边界](01-architecture/service-boundaries.md)、[术语表](06-business/glossary.md)。
