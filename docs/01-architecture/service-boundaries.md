# 服务边界

| 服务 | 数据所有权 | 不负责 |
|---|---|---|
| tenant | 租户、套餐、能力、配额 | 组织、门店、登录凭据 |
| identity | 账号、凭据、会话 | 角色和数据范围 |
| authorization | 角色、权限、Data Scope | 登录凭据 |
| organization | 组织树、区域、部门、员工、任职 | 门店经营属性 |
| store | 门店、营业属性、地址、组织归属 | 租户生命周期 |
| sales | 销售事实、预测业务版本、调整发布 | 模型计算 |
| dish | 菜品、分类、菜品预测业务版本 | 模型训练 |
| workforce | 班次、排班、用工设置、人力成本 | 考勤事实 |
| attendance | 日考勤、异常、修正、月汇总 | 排班计划 |
| inspection | 问卷、任务、记录、整改、复核 | 通用表单平台 |
| dashboard | 首页编排和卡片 View Model | 业务事实 |
| reporting | 指标、只读模型、报表和导出 | 跨库直查 |
| prediction | 模型、特征、执行和质量 | 业务审批发布 |
| ai | LLM、Agent、Prompt、工具编排 | 直接读取业务库 |
| knowledge | 文档、切片、Embedding、RAG | 普通业务存储 |
| notification | 通知、模板、推送、回执 | 业务状态变更 |
| file | 文件元数据、存储和授权 | 业务对象所有权 |
| scheduler | 触发、记录、重试和锁 | 业务任务逻辑 |
| integration | 外部系统 Adapter 与同步 | 内部业务事实 |
