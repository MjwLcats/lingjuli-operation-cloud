# Design Token V1

Token 分为 Primitive、Semantic、Component 三层。Primitive 只供 Token 构建使用；业务代码只能使用 Semantic 或 Component Token。

## 间距

`4, 8, 12, 16, 20, 24, 32, 40, 48`。

## 圆角

- sm：4
- md：6
- lg：8
- xl：12

## 字体层级

PageTitle、SectionTitle、CardTitle、Body、Secondary、Caption、Label、Number。具体跨端输出由 `@lingjuli/design-tokens` 维护。

## 颜色语义

Brand、Text、Border、Background、Fill、Status、Chart。状态至少包含 success、warning、error、processing、disabled。

禁止业务代码直接写品牌色、状态色或任意间距值。
