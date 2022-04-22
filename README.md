

## 1. 相关技术栈

1. **前端：** Thymeleaf、Layui、Ajax、JQuery
2. **后端** ： SpringBoot , MyBatis
3. **开发环境：** IDEA 、SpringBoot 2.6.6、Maven
4. **数据库**：MySQL 8.0.28
4. **服务器**：腾讯云 ubuntu20

用于测试的默认用户

| username | password | role |
| :---- | :----- | :------ |
| user1  | 123456 | 普通用户 |
| user2  | 123456 | 普通用户 |
| admin  | 123456 |  管理员  |

## 2. 需求

![图书管理系统的需求](https://hexoblogimages-1304994718.cos.ap-nanjing.myqcloud.com/202204221018639.png)

## 3. 数据库设计

![数据库设计](https://hexoblogimages-1304994718.cos.ap-nanjing.myqcloud.com/202204221022163.png)

## 4. 技术实现

未来技术实现：

1. 鉴权：使用 JWT 配合 SpringSecurity
2. 对象存储：MinIO OSS 存储桶，存放对应的电子书供借阅使用
3. 部署：Docker CICD 一套部署
4. 缓存/消息队列：防止高并发问题
5. 即时通讯：方便发布公告，或者进行通知

## 5. 扩展功能

### 5.1 UI 参考

![image-20220422104402765](https://hexoblogimages-1304994718.cos.ap-nanjing.myqcloud.com/202204221044830.png)

### 5.2 结构图片

以管理员的 controller 举例：

![admin controller](https://hexoblogimages-1304994718.cos.ap-nanjing.myqcloud.com/202204221348259.png)
