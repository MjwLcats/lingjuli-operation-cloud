# 权限模型

权限采用 RBAC + Data Scope。资源区分 Menu、Page、Action、API、Data；权限编码使用 `<domain>:<resource>:<action>`。

数据范围支持 SELF、STORE_CURRENT、STORE_ASSIGNED、REGION_CURRENT、REGION_ASSIGNED、ORG_CURRENT、ORG_AND_DESCENDANTS、ALL_TENANT 与 CUSTOM。ALL_TENANT 仅表示当前租户。
