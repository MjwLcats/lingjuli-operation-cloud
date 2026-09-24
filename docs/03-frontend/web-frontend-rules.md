# Web 前端规则

Web 端使用单一 React、TypeScript Strict、Vite 和 Ant Design 应用。平台端与商家端按顶层路由、权限域和业务模块隔离，共享应用基础设施与 Design System，不复制 Token 和基础组件。

页面负责路由参数、权限入口和组件装配。HTTP、数据转换、复杂表单、表格列、权限计算和弹窗实现分别进入 api、service、model、hooks 和 components。

服务端数据与客户端 UI 状态分离；不为了统一而引入全局 Store。路由级模块默认延迟加载，避免把平台和商家全部功能打入首屏。
