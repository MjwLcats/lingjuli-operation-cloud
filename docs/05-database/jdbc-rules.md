# Spring JDBC 规范

统一使用 NamedParameterJdbcTemplate。Repository SQL 必须显式可读，租户表的查询、更新和删除必须包含 tenant_id。

禁止通过字符串拼接排序、字段名和条件；动态结构必须使用白名单。复杂查询需要 Repository 集成测试和执行计划评估。
