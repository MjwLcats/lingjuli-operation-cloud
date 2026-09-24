# Java 编码规则

- 使用 Java 17、构造器注入和不可变 DTO。
- Controller 不捕获所有异常，不直接操作 Repository。
- Command 与 Query 用例分离，事务边界位于 Application。
- 金额使用 BigDecimal，时间点使用 Instant，业务日期使用 LocalDate。
- 禁止返回 null 集合，禁止无意义布尔参数，禁止静态 Service Locator。
- 类和方法过大时按职责拆分，而不是只移动到工具类。
