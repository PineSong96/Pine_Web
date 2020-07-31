# Pine_Best_Admin

#### 项目介绍
SpringBoot 快速开发平台

#### 软件架构
软件环境：SpringBoot 2.3.1 +JDK1.8 + MySQL 5.6 
部署环境：Windows 、Linux 、macOS
角色 ： 主要开发
系统介绍 ： 采用SpringBoot、MyBatis开发的一套快速开发平台
数据库兼容:  MYSQL/Redis

- 	目前开发主要完成度：
- 	集成Shiro权限处理（集成Redis做缓存处理）
- 	集成微信用户  微信支付
- 	提供了代码生成器 
-       可以快速根据MySQL数据库表 快速批量生成Dao、Service、Controller以及Vue页面
- 	生成后台管理页面的增删改查，只需要编写业务逻辑的代码，生成的代码。
- 	生成的接口灵活可满足95%的基本的增删改查需求 
- 	集成了swagger文档支持，方便编写API接口文档 代码生成文档自动生成
- 	集成了阿里连数据库监控连接池查看数据库操作
- 	灵活的权限控制，可控制页面满足权限需求  
- 	集成Redis
- 	涵盖了大部分 Util组件类 方便开发

#### 项目概述

###   generator 使用
-     这里是列表文本代码自动生成工具可以涵盖单表的任何条件的增删改查
-     数据库设计完成之后直接运行 pine-generator 工程
-     浏览器输入 http://localhost:8082/pine-generator/
![generator 访问页面](https://images.gitee.com/uploads/images/2019/0421/110348_334054ce_1583129.png "gemerator.png")
- 选择需要生成的表点击生成
- 解压代码包将代码直接复制到 pine-admin\src\main\java\com\pine\admin\modules\business

### Web项目运行

```
 mvn clean package -Dmaven.test.skip=true
 cd pine-admin\target>
 java -jar web.jar
```
- swagger地址 http://localhost:8888/swagger-ui.html

- 用户默认账号 root 密码 1234


![项目基本结构](https://images.gitee.com/uploads/images/2019/0421/110725_efcbc5b2_1583129.png "微信开发.png")
