# 数据库初始化

## 配置位置

本地数据库管理连接保存在仓库根目录 `.env.local`，使用以下变量：

```dotenv
MYSQL_HOST=
MYSQL_PORT=3306
MYSQL_USERNAME=
MYSQL_PASSWORD=
```

`.env.local` 已被 Git 忽略，只用于本机操作，禁止提交真实账号、密码或生产凭据。文档、日志、截图和工单中也不得记录明文密码。

## 初始化顺序

1. 使用具备建库权限的管理账号连接目标 MySQL 8 实例。
2. 执行 `database/bootstrap/V001__create_platform_databases.sql`，仅创建首批平台逻辑数据库。
3. 分别以对应数据库为目标，执行服务目录内的 Flyway Migration：tenant、identity、authorization、organization、store。
4. 检查每个数据库的 `flyway_schema_history`，确认 `V001` 状态为成功。
5. 校验数据库字符集、表数量、索引和约束。
6. 为运行环境创建每服务独立的最小权限账号；应用运行账号不得拥有建库、跨库读取或全局管理权限。

数据库地址和凭据不写入脚本。开发工具应从 `.env.local` 读取连接参数，再显式选择目标数据库。Spring Boot 服务运行时使用 `MYSQL_URL`、`MYSQL_USERNAME`、`MYSQL_PASSWORD` 注入自己的数据源；不要把仓库根目录的管理账号直接作为服务账号。

## 当前执行记录

首批 5 个平台数据库已经在指定的远程 MySQL 8 实例完成 Bootstrap 和 V001 迁移。仓库只记录数据库名、版本和结构清单，不记录服务器密码。当前事实以 [Schema 清单](schema-inventory.md) 为准。

## 后续变更

- 已执行的 V001 永久不可修改，后续变更从服务自己的 V002 开始。
- 新服务按需创建数据库并执行自己的迁移，不要求一次性创建全部规划数据库。
- 任何手工修复都必须转化为前向 Migration，确保其他环境可以重复执行。
