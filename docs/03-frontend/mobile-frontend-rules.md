# Mobile 前端规则

移动端使用 uni-app x、Vue 3、TypeScript、UVUE 和 UTS。页面围绕执行任务设计，不复制 PC 管理后台。

平台差异必须通过 `platform/contracts` 与各平台实现封装。业务页面不得散布 iOS、Android 或 HarmonyOS 条件。所有接口动态数据先校验/转换，再进入强类型模型。
