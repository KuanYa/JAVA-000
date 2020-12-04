学习笔记

### 一、搭建一主一从笔记

````
操作系统：win10
mysql版本：mysql-5.7.30-winx64
````

* **踩坑一**

  本人电脑已经存在一个`mysql`服务，但是在初始化`mysqld --initialize-insecure` 时，会出现如下错误：

  ````
  2020-12-02T13:30:11.905732Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).
  2020-12-02T13:30:11.905790Z 0 [Warning] 'NO_ZERO_DATE', 'NO_ZERO_IN_DATE' and 'ERROR_FOR_DIVISION_BY_ZERO' sql modes should be used with strict mode. They will be merged with strict mode in a future release.
  2020-12-02T13:30:11.905794Z 0 [Warning] 'NO_AUTO_CREATE_USER' sql mode was not set.
  2020-12-02T13:30:11.908183Z 0 [ERROR] --initialize specified but the data directory has files in it. Aborting.
  2020-12-02T13:30:11.908956Z 0 [ERROR] Aborting
  ````

  经过分析，原因是电脑配置了`mysql`的环境变量，导致在初始化时，会读取已经执行过`mysqld --initialize-insecure`命令的服务文件夹下的`data`,建议是删除环境变量，操作命令直接在`/bin`目录下操作。

* **踩坑二**

  当已经将两个`mysql`服务初始化成功后，出现了，两个服务不能同时启动的问题。

  解决办法为：两个`mysql`服务不能放在同一个盘符下，例如 分别放在F、G 两个系统盘中，经过初始化安装服务后，可以同时启动两个`mysql`服务。

* 验证主从是否成功

  * 登录主库查看表

    ````
    mysql> show tables;
    +--------------+
    | Tables_in_db |
    +--------------+
    | t1           |
    +--------------+
    1 row in set (0.00 sec)
    
    mysql> use db
    Database changed
    mysql> show tables;
    +--------------+
    | Tables_in_db |
    +--------------+
    | t1           |
    +--------------+
    1 row in set (0.00 sec)
    ````

  * 登录从库查看表

    ````
    mysql> show tables;
    +--------------+
    | Tables_in_db |
    +--------------+
    | t1           |
    +--------------+
    1 row in set (0.00 sec)
    ````

  * 主库与从库都存在表`t1`，在主库执行如下命令

    ````
    mysql> drop table t1;
    Query OK, 0 rows affected (0.20 sec)
    ````

  * 去从库执行如下命令

    ````
    mysql> show tables;
    Empty set (0.00 sec)
    ````

  * 总结：通过对比发现，主库执行删除表语句，从库也会将对应表删除，证明主从搭建成功，详细说明请查看`ms.md`。

    
  
### 二、搭建一主一从笔记


​    

