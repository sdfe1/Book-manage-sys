# 📚 图书管理系统 

---

## 🏗️ 项目架构
### 架构模式
🌟**模块化开发架构** + **MVC分层设计**，采用前后端分离开发模式：
- **book-server**：主服务模块（SpringBoot应用）
    - 控制层：`controller`包处理HTTP请求
    - 服务层：`service`包实现业务逻辑
    - 数据层：`mapper`包操作数据库
- **book-pojo**：领域模型模块
    - 实体类：`entity`包定义数据库映射对象
    - DTO：`dto`包封装接口传输对象
    - VO：`vo`包封装接口返回对象
- **book-common**：通用模块
    - 工具类：`utils`包存放通用工具
    - 配置类：`config`包管理全局配置
    - 异常处理：`exception`包定义全局异常

---
## 🛠️ 技术栈
- **核心框架**: SpringBoot 3.1.5
- **ORM框架**: MyBatis 3.0.4
- **数据库**: MySQL 8.0.33
- **安全认证**: So-Token 1.40.0

---

## 🎯 功能模块
### 基础功能实现
#### 用户端功能
- 🔍 图书查询
- 📚 分类浏览
- ❤️ 书籍收藏管理
- 👥 用户关注系统
- 💬 评论互动
- 📝 个人资料维护

#### 管理端功能
- 📖 图书生命周期管理
- 🗂️ 分类体系维护

#### 认证功能（通用）
- 🔒 登录认证
- 🔑 角色认证

---

## 📡 API接口规范
### 接口分类
| 模块       | 接口描述       | 请求方式与路径                                                                                                           | 
|----------|------------|-------------------------------------------------------------------------------------------------------------------|
| **认证模块** |            |                                                                                                                   |      
|          | 用户登录       | `POST /auth/login`                                                                                                | 
|          | 用户注册       | `POST /auth/register`                                                                                             | 
|          | 用户注销       | `POST /auth/logout`                                                                                               |
| **图书模块** |            |                                                                                                                   |     
|          | 新增图书       | `GET /admin/books`                                                                                                | 
|          | 删除图书     | `DELETE/admin/books/{id}`                                                                                         | 
|          | 更新图书   | `PUT /admin/books/{id}`                                                                                           | 
|          | 获取所有图书列表     | `GET /admin/books`    `GET /user/books`                                                                           | 
|          | 获取单本图书信息    | `GET /admin/books/{id}`  `GET /user/books/{id}`                                                                   | 
|          |      为图书设置分类      | ` POST   /admin/books/{bookId}/categories/{categoryId} `                                                          |
|          |     删除图书分类       | `DELETE     /admin/books/{bookId}/categories/{categoryId} `                                                       |
|          |获取图书的分类名| `  GET    /admin/books/{bookId}/categories             `  `  GET    /user/books/{bookId}/categories             ` |
| **分类模块** |            |                                                                                                                   |  
|          | 新增分类     | `POST /admin/categories`                                                                                          | 
|          | 删除分类 | `DELETE /admin/categories/{id}`                                                                                   | 
|          | 更新分类     | `PUT /admin/categories/{id}`                                                                                      | 
|          |根据Id获取分类| ` GET /admin/categories/{id} `                                                                                    |
|          |获取所有分类| `GET   /admin/categories   `    `GET   /user/categories   `                                                       |
|          |根据分类Id获取图书| `GET        /admin/categories/{categoryId}/books `    `GET        /user/categories/{categoryId}/books `           |
| **收藏模块** |            |                                                                                                                   |   
|          | 收藏图书       | `POST /users/{userId}/favorites/{bookId}`                                                                         | 
|          | 取消收藏       | `DELETE /users/{userId}/favorites/{bookId}`                                                                       | 
|          | 获取用户收藏列表   | `GET /users/{userId}/favorites`                                                                                   | 
| **评论模块** |            |                                                                                                                   |     
|          | 发表评论       | `POST /user/books/{bookId}/reviews`                                                                               | 
|          | 回复评论       | `POST /user/books/{bookId}/reviews/{reviewId}/replies`                                                            | 
|          | 删除评论       | `DELETE /user/books/{bookId}/reviews/{reviewId}`                                                                  | 
|          | 获取图书评论列表   | `GET /user/books/{bookId}/reviews`                                                                                | 
| **关注模块** |            |                                                                                                                   |       
|          | 取消关注       | `DELETE /users/{userId}/follow`                                                                                   | 
|          | 关注用户       | `POST /users/{userId}/follow`                                                                                     | 
|          | 获取用户粉丝列表）  | `GET /users/{userId}/followers`                                                                                   | 
|          | 获取用户关注列表   | ` GET     /users/{userId}/following `                                                                             | 







