# 日志规范

结构化日志至少包含 serviceName、environment、traceId、requestId、tenantId、userId、operation、result、durationMs、errorCode。

禁止记录密码、Token、密钥、完整敏感个人信息和大段文档正文。安全日志、业务审计与运行日志分别治理。
