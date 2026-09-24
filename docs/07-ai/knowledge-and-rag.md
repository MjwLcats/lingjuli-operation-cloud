# 知识库与 RAG

文件上传后由知识服务建立文档和版本记录，使用 POI/PDFBox 解析、清洗和切片，再通过 Embedding Provider 写入 Milvus。

MySQL 保存权威元数据、正文引用、权限与处理状态；Milvus 仅保存向量及必要过滤字段。所有检索必须包含 Tenant 和知识权限过滤。
