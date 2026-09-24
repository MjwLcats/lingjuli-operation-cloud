# 数据库规范

数据库和对象使用 snake_case。核心表统一考虑 id、tenant_id、created_by、created_at、updated_by、updated_at、deleted、version，但字段应按真实归属使用，禁止所有表无脑添加 store_id。

金额使用 DECIMAL，比例明确精度和单位，时间统一时区策略。索引名称使用 `uk_<table>_<columns>` 和 `idx_<table>_<columns>`。
