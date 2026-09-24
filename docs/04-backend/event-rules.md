# 事件规范

事件使用过去式稳定名称，如 `inspection.task.published`。事件信封包含 eventId、eventType、eventVersion、occurredAt、tenantId、source、traceId 和 data。

发布使用 Transactional Outbox；消费者必须幂等，支持重试与死信。事件不得包含密码、Token 或不必要的个人信息。
