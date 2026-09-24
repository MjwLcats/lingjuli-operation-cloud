# ADR-0002：采用领域微服务

状态：Accepted

后端预拆为多个可运行 Spring Cloud 服务。服务按稳定业务能力命名，禁止 `operation`、`system` 等宽泛容器。服务数量不等于默认全部部署。
