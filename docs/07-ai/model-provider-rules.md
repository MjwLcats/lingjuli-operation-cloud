# 模型 Provider 规范

Ollama 是本地模型 Provider，不应渗透到业务代码。模型调用通过 Provider 接口，记录模型、版本、耗时、Token/资源用量和结果状态。密钥与地址来自安全配置，不写入仓库。
