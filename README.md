# personalblog
一个二次元的个人博客网站，基于SpringBoot+Mybatis开发，语言采用的是Java<br>

运行环境需要：Intellij IDEA、MySql、Redis、tomcat<br>

项目管理工具为Gradle
# 写在前面
此项目页面采取了[Shawnzeng](https://shawnzeng.com)大佬的博客前端（做了几个地方的修改）以及开源博客tale博客的后端页面<br>
[Shawnzeng](https://shawnzeng.com)大佬的博客是wordpress的一个主题。如果感兴趣可以去他的博客网购买：https://shawnzeng.com <br>
而tale开源项目的github地址为：https://github.com/otale/tale <br>
本项目的预览地址为：http://suyeq.com:8080 <br>
ps：本项目会持续更新以及优化。。。。图片太大以及服务器不好，所以加载慢的一批，也许优化做的不好，bug还是有些的，哈哈哈<br>

上一张效果图：
![首页图](https://github.com/suyeq/personalblog/blob/master/image/1.png)


# 运行
1. 运行本项目下的名为Sql语句文件里的语句，创建数据库以及对应的表，里面已经含有预览地址网站的数据
2. 图片服务器用的是tomcat，在本项目的Spring Boot配置文件下配置一下，当然你也可以选择其他的图片服务器，同时也要修改redis以及其他的零散配置
3. 其余配置请见配置文件


# 包含的功能
* 文章展示，文章分为说说与博客两类
* 游客浏览与评论
* 文章归档
* 友链展示
* Live2D的动漫人物
* 基于markdown的文章发布
* 3D便签云
* 文章分类
* 音乐播放器
* 最受欢迎与最近评论
* 一言与验证码验证
* 文章搜索
* 后台评论、分类、标签的管理
* 文章的保存与修改
* 系统日志
* 基于netty与dropzone的多文件的上传
* 敏感词的过滤
* 在线编辑html页面
* 邮件回复
# 覆盖的知识点
* SpringBoot
* Mybatis
* Netty
* dropzone
* aplayer音乐播放器
* DFA算法
* Pjax
* Thymleaf
* Spring mail
* Redis
* Spring 拦截器

