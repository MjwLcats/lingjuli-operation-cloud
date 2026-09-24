# 前端目录规范

Web 应用使用 `app / pages / modules / features / entities / shared` 分层。依赖方向为 pages → modules/features → entities → shared/design-system。

业务模块内部使用 `api / services / model / hooks / components / pages`。跨模块只能从公开入口导入，禁止深路径访问内部实现。
