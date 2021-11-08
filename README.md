# springcloud-oauth2-blocks
![Build Status](https://img.shields.io/badge/blocks-1.0-success.svg) ![Build Status](https://img.shields.io/badge/Spring%20cloud-2020-blue.svg) ![Build Status](https://img.shields.io/badge/SpringBoot-2.4-blue.svg) 
## 系统说明

- 基于 Spring Cloud 2020 、Spring Boot 2.4、 OAuth2 的 RBAC **权限管理系统**
- 基于数据驱动视图的理念封装 element-ui，即使没有 vue 的使用经验也能快速上手
- 提供 lambda 、webflux 的生产实践

## 快速开始

### 核心依赖

| 依赖                   | 版本     |
| ---------------------- | -------- |
| Spring Boot            | 2.4.2    |
| Spring Cloud           | 2020.0.1 |
| Spring Cloud Alibaba   | 2021.1   |
| Spring Security OAuth2 | 2.3.6    |
| Mybatis Plus           | 3.4.3.4  |
| hutool                 | 5.7.14   |
### 模块说明
在线文档：<https://www.yuque.com/books/share/60e932cd-7c05-41ba-986d-281bbf5503b0?# 《blocks》>
```markdown
模块说明blocks    
springcloudplus
├── spring-cloud-auth -- 授权服务提供[3000] [未完成]
└── spring-cloud-commons -- 系统公共模块
     ├── block-swagger -- swagger模块
     ├── block-test -- 测试模块
├── pig-register -- Nacos Server[8848]
├── spring-cloud-gateway -- Spring Cloud Gateway网关[9527] 
```
