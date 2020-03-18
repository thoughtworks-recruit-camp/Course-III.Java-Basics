## 作业描述

用JDBC实现StudentRepository类，使的程序能够通过数据库管理学生。

## 提示&要求

- 使用本地数据库，并自行创建Student类对应的表
- 连接数据库时需要添加额外的参数，不然会出现中文乱码和时间错乱的问题，如
    ```
    jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong
    ```
