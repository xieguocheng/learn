
## 子鹿班课后台管理系统 ##
# 项目介绍 #
- learn-子鹿班课后台管理系统项目提供了PC Web端后台管理员可以通过登录后台进行后台管理，主要包括：题库管理、课程管理（包括课程作业，实验管理）、分类管理、用户管理以及系统统计管理。为后台管理提供直观的数据统计，同时PC Web端提供了用户登录上传实验操作。主要采用技术:spring boot 2.0.5+spring+Spring Mvc+mybatis+Spring Security+Jquery+Thymeleaf+Bootstrap+webUpLoad+H-ui+MySQL+redis+Nginx

- learn-子鹿班课小程序端可以通过点击查看：[Learn-小程序端](https://github.com/xieguocheng/learn "learn小程序")，该小程序端主要提供用户可以创建课程，加入课程等一系列功能，这里不做过多的介绍
# 技术选型 #
-  后端使用技术
	-  jdk：1.8
	-  数据库：MySQL5.7
	-  核心框架：spring boot 2.0.5.RELEASE
	-  安全框架：Spring Security
	-  视图框架：spring mvc
	-  持久层框架：MyBatis MyBatis-Plus 3.1.0
	-  缓存：redis、ehcache
	-  定时器：Scheduled
	-  文档导出：poi3.11
	-  文件上传依赖：qiniu[7.2.0, 7.2.99]
	-  邮件：mail1.4.5
	-  短信：aliyun3.2.3
	-  插件：lombok
-  前端使用技术
	-  前端框架：H-UI，Bootstrap
	-  js框架：jQuery
	-  图表框架：Echarts
	-  表格插件：Datatables
	-  文件上传：webUpLoad
	-  模板引擎：Thymeleaf
# 项目结构 #
> 
java/com/learn：
<br/>|--config 系统配置
<br/>|--dto 实体类扩充属性
<br/>|--enums 枚举常量
<br/>|--mapper dao层操作文件
<br/>|--pojo 实体类
<br/>|--security 安全框架配置
<br/>|--service 服务层
<br/>|--task 任务调度
<br/>|--utils 工具类
<br/>|--web controller控制层
<br/>|--LearnApplication 启动类
<br/>resuorces：
<br/>|--mapper dao层配置文件
<br/>|--statis 静态资源文件
<br/>|--template 前端html网页文件
<br/>|--application.properties 系统总配置
# 安装教程 #
略
# 项目展示 #
- 后台登录界面




