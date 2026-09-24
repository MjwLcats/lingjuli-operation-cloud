# 状态系统

业务状态使用统一 Enum、Dictionary 和 Status Metadata，至少包含 code、label、tone、icon、description。Web 与 Mobile 共享 code 和语义，分别映射视觉组件。

禁止页面使用 `if status === 1` 直接返回中文和颜色。
