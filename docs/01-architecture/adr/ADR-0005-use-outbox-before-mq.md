# ADR-0005：先建立 Outbox，再启用 MQ

状态：Accepted

跨服务异步协作从第一天使用版本化事件和 Transactional Outbox。RocketMQ 在首个真实异步业务落地时启用，避免基础设施先于需求膨胀。
