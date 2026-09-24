# 项目总览

Lingjuli Operation Cloud 是餐饮行业多租户 SaaS 运营平台。平台覆盖 SaaS 平台治理、集团/区域/门店管理、销售、菜品、用工、考勤、巡检、工作台、报表、AI、预测与知识库。

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

参见：[总体架构](01-architecture/architecture.md)、[服务边界](01-architecture/service-boundaries.md)、[术语表](06-business/glossary.md)。
