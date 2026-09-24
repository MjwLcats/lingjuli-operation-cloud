# Database

正式 Migration 跟随拥有数据的服务，位于各服务 `src/main/resources/db/migration`。本目录保存跨服务数据库约定和实例级 Bootstrap，不承载业务表的集中 SQL 所有权。

## 目录职责

- `bootstrap/`：创建逻辑数据库等实例级前置资源，不创建业务表。
- `conventions/`：数据库命名、数据类型等跨服务规范。
- `backend/**/<service>/src/main/resources/db/migration/`：由对应服务拥有并执行的表结构与基础数据 Migration。

当前 Bootstrap 创建首批 5 个平台逻辑数据库。执行方法、现状和后续规则见 [数据库初始化](../docs/05-database/database-initialization.md)、[Schema 清单](../docs/05-database/schema-inventory.md) 与 [Flyway 规范](../docs/05-database/flyway-rules.md)。
