# 后端架构

后端采用预拆分领域微服务。Gateway 使用 WebFlux，业务服务使用 Spring MVC。每个服务可独立启动、测试、迁移和发布，通过 Nacos 注册发现。

服务内部使用 interfaces、application、domain、infrastructure 分层。跨服务通信不得破坏数据库所有权。
