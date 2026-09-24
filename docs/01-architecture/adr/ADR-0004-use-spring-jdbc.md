# ADR-0004：使用 Spring JDBC

状态：Accepted

数据访问使用 NamedParameterJdbcTemplate，不引入 ORM。租户 SQL 明确包含 Tenant 条件，并通过 Repository 约束、静态检查和双租户集成测试保证隔离。
