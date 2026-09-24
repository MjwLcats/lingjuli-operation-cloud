# 审计字段

`created_at`、`updated_at` 保存统一时间语义；`created_by`、`updated_by` 保存稳定主体 ID。逻辑删除字段使用 `deleted`，并明确唯一索引在删除场景下的行为。并发更新需要时使用 `version` 乐观锁。
