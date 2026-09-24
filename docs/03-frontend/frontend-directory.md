# 前端目录规范

Web 使用单一 `apps/lingjuli-web` 应用。平台端与商家端在应用内按顶层路由、权限域和模块公开入口隔离，共享启动设施、Design System 和基础组件；禁止通过复制应用形成两套规范。

Web 应用使用 `app / pages / modules / features / entities / shared` 分层。依赖方向为 pages → modules/features → entities → shared/design-system。

业务模块内部使用 `api / services / model / hooks / components / pages`。跨模块只能从公开入口导入，禁止深路径访问内部实现。

`apps/lingjuli-mobile` 独立构建和发布，与 Web 共享视觉语义而非运行时代码。Web 的部署、路由或依赖升级不得改变 App 构建产物。
