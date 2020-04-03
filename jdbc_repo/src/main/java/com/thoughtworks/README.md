# `JDBC-Repo Demo`
## 使用说明
1. 在数据库终端中运行`jdbc_demo_student.sql`中的命令完成demo所用的建库建表操作；
2. 修改App中`FIXME`注释所在处的数据库连接、用户名与密码以匹配个人数据库；
3. 修改App中`FIXME`注释所在处分别使用`StudentRepositoryNew`与`StudentRepositoryOld`运行Demo，查看输出结果，并可以通过调试工具查看运行流程；

## 其他说明
`build.gradle`中默认使用阿里云的maven仓库，如果之前习惯使用代理连接central仓库的话， 可能需要修改仓库设置或者关掉代理