# Lingjuli Operation Cloud 工程规则

## 适用范围

本文件约束整个仓库。子目录可通过更近的 `AGENTS.md` 增加更严格的规则，但不得放宽本文约束。修改前先阅读相关模块 README、领域文档与 ADR。

## 固定技术栈

- Web：React、TypeScript Strict、Vite、Ant Design、函数组件与 Hooks。
- Mobile：uni-app x、Vue 3、TypeScript、UVUE、UTS，支持 iOS、Android、HarmonyOS NEXT 与 H5。
- Backend：Java 17、Spring Boot 3.2.4、Spring Cloud 2023.0.1、Spring Cloud Alibaba 2023.0.1.0、Spring MVC；Gateway 使用 WebFlux。
- Data：Spring JDBC、MySQL 8、Redis、Flyway、Milvus。禁止擅自引入 JPA/MyBatis。
- Build：pnpm workspace 与 Maven 多模块。

## 工作顺序

需求理解 → 领域归属 → 复用检查 → Design System 检查 → 数据模型 → 权限 → API → 数据库 → 实现 → 测试 → 一致性检查 → 文档。

修改既有功能前必须阅读调用链、数据库、API、页面、公共组件和测试，优先渐进修改。

## 服务边界

- 服务使用 `lingjuli-<domain>-service` 命名，Java 包使用 `com.lingjuli.<domain>`。
- 禁止创建 `operation`、`system`、`business`、`base-service` 等无清晰所有权的容器模块。
- 服务只能访问自己拥有的数据库；禁止跨服务 Repository、Entity、SQL 和表访问。
- 同步协作通过版本化 Internal API，异步协作通过版本化事件。
- Controller 只处理协议、校验和用例调用；禁止业务逻辑与直接 SQL。
- Application 按 command/query 用例拆分；禁止万能 Service。
- Domain 不依赖 HTTP、JDBC 实现或外部 SDK。
- Foundation 仅容纳稳定技术能力，禁止成为业务垃圾桶。

## SaaS、权限和数据

- Tenant 是 SaaS 隔离边界，Organization、Region、Store 是租户内部组织/经营实体。
- 所有租户业务表、查询、缓存、事件、任务、文件、导出、向量与日志必须带租户语义。
- 不信任客户端提供的 Tenant 或 Data Scope；后端执行最终鉴权。
- 权限采用 RBAC + Data Scope；前端隐藏不是安全边界。
- 平台跨租户能力必须使用专门入口、显式授权并审计。
- 金额使用 `DECIMAL`/`BigDecimal`，禁止 float/double。

## 数据库与 Flyway

- 每服务一个逻辑数据库和独立 Flyway 历史。
- Migration 位于服务的 `src/main/resources/db/migration`，使用 `V###__description.sql`。
- 已执行 Migration 永不修改；所有结构和种子数据变更必须新增 Migration。
- 禁止生产手工改表，禁止运行时自动 DDL。
- 表和字段必须有注释；更新、删除必须包含 Tenant 条件。

## API、异常和日志

- 产品 API：`/api/v1/**`；内部 API：`/internal/v1/**`；开放 API：`/openapi/v1/**`。
- 返回使用统一 `ApiResponse<T>`；分页格式、错误码与校验错误统一。
- 日志必须包含 serviceName、traceId、requestId、tenantId、userId、operation、durationMs、errorCode。
- 禁止记录密码、Token、密钥及未脱敏个人信息。

## Web 与 Design System

- 业务页面只能使用语义 Token 或组件 Token，不得随意写颜色、间距、圆角和状态色。
- 顺序固定：Foundation → Token → Base Component → Business Component → Template → Page。
- 页面负责路由、权限入口和组合；请求、复杂状态、表格配置、表单 Schema、Modal/Drawer 与业务计算必须拆分。
- HTTP 统一经过 API/Service；禁止组件内直接请求，禁止大量 `any`。
- 已有公共组件可满足时禁止重复实现。
- 所有页面考虑 loading、empty、error、noPermission、disabled、partialData 和 success。

## Mobile

- Mobile 与 Web 共享品牌、状态、字号、间距、圆角和图标语义，但采用移动端布局和点击尺寸。
- 平台差异封装在 `platform`，禁止业务页面散布平台判断。
- Mobile 是执行工具，不复制 PC 管理后台。

## 测试与交付

- 核心权限、多租户、预测、排班、考勤、巡检与统计必须有自动化测试。
- 后端至少运行 `mvn test`；Web 至少运行 lint、typecheck 与 build。
- API、Schema、权限或事件变更必须同步测试与文档。
- 不得声称未实际执行的验证已经通过。

## 禁止事项

- 禁止将页面、Service、Common 持续堆成超大文件或万能模块。
- 禁止跨服务访问数据库、复制业务 SQL、绕过 Flyway。
- 禁止 Tenant 与 Store 混用、权限只做前端控制。
- 禁止业务状态直接写字符串或各页面自行定义颜色。
- 禁止提交密钥、真实客户数据、上传文件、日志、构建产物和依赖缓存。
