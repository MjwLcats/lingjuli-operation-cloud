# 数据库规范

数据库和对象使用 snake_case。核心表统一考虑 id、tenant_id、created_by、created_at、updated_by、updated_at、deleted、version，但字段应按真实归属使用，禁止所有表无脑添加 store_id。

金额使用 DECIMAL，比例明确精度和单位，时间统一时区策略。索引名称使用 `uk_<table>_<columns>` 和 `idx_<table>_<columns>`。

每个服务拥有一个 `lingjuli_<domain>` 逻辑数据库和独立的 `flyway_schema_history`。服务只能访问自己拥有的数据库；跨服务数据通过版本化 API 或事件协作，禁止跨库 Join、直接读表和复制他方业务 SQL。

建库属于实例级 Bootstrap，建表及基础数据属于服务级 Flyway Migration。当前数据库及表的实际状态见 [Schema 清单](schema-inventory.md)。
