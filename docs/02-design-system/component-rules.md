# 组件规则

基础组件只表达视觉和交互；业务组件表达稳定领域选择或展示；页面模板固定页面结构。

Web 基础组件包括 AppPage、PageHeader、PageSection、AppCard、SearchForm、DataTable、StatusTag、MetricCard、EmptyState、ErrorState、PermissionButton、AppModal、AppDrawer、AppForm、AppSelect、AppDateRange、AppUploader。

组件必须覆盖 loading、empty、error、disabled、noPermission 等状态，并提供一致的 focus、hover、selected 与 disabled 行为。
