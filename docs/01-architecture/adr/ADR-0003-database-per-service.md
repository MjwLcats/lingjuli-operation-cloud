# ADR-0003：每服务拥有逻辑数据库

状态：Accepted

第一阶段允许共享 MySQL 实例，但每服务拥有独立逻辑数据库、账号、Flyway 历史和表所有权。禁止跨服务 SQL 与直接 Join。
