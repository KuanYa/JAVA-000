学习笔记



### 作业一：

设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github

* **作业一思路**
  * 拆分 2 个库，每个库 16 张表 使用` ShardingSphere-Proxy `中间件完成。
  * 常见的增删改查则使用`ShardingSphere-JDBC`中间件完成
  * 两个中间件配置相同规则即可

* **实现操作步骤如下**

#### 一、首先下载[ShardingSphere-Proxy](https://mirror.bit.edu.cn/apache/shardingsphere/5.0.0-alpha/apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin.tar.gz)最新版本

* 配置`server.xml`

  ```yaml
  #
  # Licensed to the Apache Software Foundation (ASF) under one or more
  # contributor license agreements.  See the NOTICE file distributed with
  # this work for additional information regarding copyright ownership.
  # The ASF licenses this file to You under the Apache License, Version 2.0
  # (the "License"); you may not use this file except in compliance with
  # the License.  You may obtain a copy of the License at
  #
  #     http://www.apache.org/licenses/LICENSE-2.0
  #
  # Unless required by applicable law or agreed to in writing, software
  # distributed under the License is distributed on an "AS IS" BASIS,
  # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  # See the License for the specific language governing permissions and
  # limitations under the License.
  #
  
  ######################################################################################################
  # 
  # If you want to configure governance, authorization and proxy properties, please refer to this file.
  # 
  ######################################################################################################
  #
  #governance:
  #  name: governance_ds
  #  registryCenter:
  #    type: ZooKeeper
  #    serverLists: localhost:2181
  #    props:
  #      retryIntervalMilliseconds: 500
  #      timeToLiveSeconds: 60
  #      maxRetries: 3
  #      operationTimeoutMilliseconds: 500
  #  overwrite: false
  
  authentication:
    users:
      root:
        password: 
      #sharding:
       # password: sharding 
        authorizedSchemas: 
  
  props:
  #  max-connections-size-per-query: 1
  #  acceptor-size: 16  # The default value is available processors count * 2.
  #  executor-size: 16  # Infinite by default.
  #  proxy-frontend-flush-threshold: 128  # The default value is 128.
  #    # LOCAL: Proxy will run with LOCAL transaction.
  #    # XA: Proxy will run with XA transaction.
  #    # BASE: Proxy will run with B.A.S.E transaction.
  #  proxy-transaction-type: LOCAL
  #  proxy-opentracing-enabled: false
  #  proxy-hint-enabled: false
  #  query-with-cipher-column: true
    sql-show: false
  #  check-table-metadata-enabled: false
  
  ```

* 配置`config-sharding.yaml`

  ```yaml
  #
  # Licensed to the Apache Software Foundation (ASF) under one or more
  # contributor license agreements.  See the NOTICE file distributed with
  # this work for additional information regarding copyright ownership.
  # The ASF licenses this file to You under the Apache License, Version 2.0
  # (the "License"); you may not use this file except in compliance with
  # the License.  You may obtain a copy of the License at
  #
  #     http://www.apache.org/licenses/LICENSE-2.0
  #
  # Unless required by applicable law or agreed to in writing, software
  # distributed under the License is distributed on an "AS IS" BASIS,
  # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  # See the License for the specific language governing permissions and
  # limitations under the License.
  #
  
  ######################################################################################################
  # 
  # Here you can configure the rules for the proxy.
  # This example is configuration of sharding rule.
  # 
  ######################################################################################################
  #
  #schemaName: sharding_db
  #
  #dataSourceCommon:
  #  username: postgres
  #  password: postgres
  #  connectionTimeoutMilliseconds: 30000
  #  idleTimeoutMilliseconds: 60000
  #  maxLifetimeMilliseconds: 1800000
  #  maxPoolSize: 50
  #  minPoolSize: 1
  #  maintenanceIntervalMilliseconds: 30000
  #
  #dataSources:
  #  ds_0:
  #    url: jdbc:postgresql://127.0.0.1:5432/demo_ds_0?serverTimezone=UTC&useSSL=false
  #  ds_1:
  #    url: jdbc:postgresql://127.0.0.1:5432/demo_ds_1?serverTimezone=UTC&useSSL=false
  #
  #rules:
  #- !SHARDING
  #  tables:
  #    t_order:
  #      actualDataNodes: ds_${0..1}.t_order_${0..1}
  #      tableStrategy:
  #        standard:
  #          shardingColumn: order_id
  #          shardingAlgorithmName: t_order_inline
  #      keyGenerateStrategy:
  #        column: order_id
  #        keyGeneratorName: snowflake
  #    t_order_item:
  #      actualDataNodes: ds_${0..1}.t_order_item_${0..1}
  #      tableStrategy:
  #        standard:
  #          shardingColumn: order_id
  #          shardingAlgorithmName: t_order_item_inline
  #      keyGenerateStrategy:
  #        column: order_item_id
  #        keyGeneratorName: snowflake
  #  bindingTables:
  #    - t_order,t_order_item
  #  defaultDatabaseStrategy:
  #    standard:
  #      shardingColumn: user_id
  #      shardingAlgorithmName: database_inline
  #  defaultTableStrategy:
  #    none:
  #  
  #  shardingAlgorithms:
  #    database_inline:
  #      type: INLINE
  #      props:
  #        algorithm-expression: ds_${user_id % 2}
  #    t_order_inline:
  #      type: INLINE
  #      props:
  #        algorithm-expression: t_order_${order_id % 2}
  #    t_order_item_inline:
  #      type: INLINE
  #      props:
  #        algorithm-expression: t_order_item_${order_id % 2}
  #  
  #  keyGenerators:
  #    snowflake:
  #      type: SNOWFLAKE
  #      props:
  #        worker-id: 123
  
  ######################################################################################################
  #
  # If you want to connect to MySQL, you should manually copy MySQL driver to lib directory.
  #
  ######################################################################################################
  
  schemaName: sharding_db
  #
  dataSourceCommon:
    username: root
    password:
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
    minPoolSize: 1
    maintenanceIntervalMilliseconds: 30000
  # 设置数据源
  dataSources:
    ds0:
      url: jdbc:mysql://localhost:3317/ds0?serverTimezone=UTC&useSSL=false 
    ds1:
      url: jdbc:mysql://localhost:3318/ds1?serverTimezone=UTC&useSSL=false
  #
  rules:
    # 配置分片规则
    - !SHARDING
      tables:
        # 配置 t_order 表规则
        t_order: # 逻辑表
          actualDataNodes: ds${0..1}.t_order${0..15} # 2个库 16张表 
          # 配置分库策略
          databaseStrategy:
            standard:
              shardingColumn: user_id 
              shardingAlgorithmName: database_inline
          # 配置分表策略
          tableStrategy:
            standard:
              shardingColumn: order_id
              shardingAlgorithmName: table_inline
      # 配置分片算法
      shardingAlgorithms:
        database_inline: # 分片算法名称
          type: INLINE # 分片类型
          props:
            algorithm-expression: ds${user_id % 2} # 分片算法属性配置
        table_inline: # 分表算法名称
          type: INLINE # 分表类型
          props:
            algorithm-expression: t_order_${order_id % 16} # 分表算法属性配置
  #  
  #  keyGenerators:
  #    snowflake:
  #      type: SNOWFLAKE
  #      props:
  #        worker-id: 123
  
  ```

#### 二、下载mysql 驱动[mysql-connector-java-5.1.47](https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.47/mysql-connector-java-5.1.47.jar)

  * 将下载的驱动放在`apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin\apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin\lib` 下即可

  * 执行`start.bat`启动 `shardingsphere-proxy`,查看日志已成功启动

    ```js
    Starting the ShardingSphere-Proxy ...
    [INFO ] 16:45:37.724 [main] ShardingSphere-metadata - Loading 0 tables' meta data for unconfigured tables.
    [INFO ] 16:45:37.730 [main] ShardingSphere-metadata - Loading 0 tables' meta data for unconfigured tables.
    [INFO ] 16:45:37.738 [main] ShardingSphere-metadata - Loading 16 tables' meta data for unconfigured tables.
    [INFO ] 16:45:37.740 [main] ShardingSphere-metadata - Loading 16 tables' meta data for unconfigured tables.
    [INFO ] 16:45:37.748 [main] o.a.s.i.c.s.SchemaContextsBuilder - Load meta data for schema sharding_db finished, cost 102 milliseconds.
    Thanks for using Atomikos! Evaluate http://www.atomikos.com/Main/ExtremeTransactions for advanced features and professional support
    or register at http://www.atomikos.com/Main/RegisterYourDownload to disable this message and receive FREE tips & advice
    [INFO ] 16:45:37.910 [main] o.a.s.p.i.i.AbstractBootstrapInitializer - Database name is `MySQL`, version is `5.7.30-log`
    [INFO ] 16:45:40.687 [main] o.a.s.p.frontend.ShardingSphereProxy - ShardingSphere-Proxy start success.
    ```

  **至此，已经完成`shardingsphere-proxy`的配置和启动，下面进行表的创建**


#### 三、使用`shardingsphere-proxy`分表
* 执行命令如下：`./mysql -uroot -h 127.0.0.1 -P3307`  （3307 为 `shardingsphere-proxy`默认端口号）
  ![image-20201213165128628](D:\Java-training-camp\JAVA-000\JAVA-000\Week_08\README.assets\shardingsphere-proxy.png)

* 使用配置的逻辑数据库 `user sharding_db `  ，执行创建表语句

  ```sql
  -- t_order 就是config-sharding.yaml 中设置的逻辑表名称
  CREATE TABLE `t_order` (
    `order_id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` BIGINT (20) NOT NULL COMMENT '用户id',
    `order_num` VARCHAR (128) NOT NULL COMMENT '订单编码',
    `order_status` INT (11) NOT NULL COMMENT '订单状态',
    `logistics_status` INT (11) NOT NULL COMMENT '物流状态',
    `order_amount` DECIMAL (18, 3) NOT NULL COMMENT '订单金额',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `creator` BIGINT (20) NOT NULL COMMENT '创建人',
    `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
    `modified_by` BIGINT (20) DEFAULT NULL COMMENT '修改人',
    `is_deleted` TINYINT (1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`order_id`)
  ) AUTO_INCREMENT = 1 COMMENT = '订单表' ;
  ```

* 分别登录 `config-sharding.yaml`中设置的两个数据库，查看是否已经分表成功

  ![image-20201213165615481](D:\Java-training-camp\JAVA-000\JAVA-000\Week_08\README.assets\image-20201213165615481.png)
  
  ​       ![image-20201213165943419](D:\Java-training-camp\JAVA-000\JAVA-000\Week_08\README.assets\image-20201213165943419.png)

**至此，已经完成基本的`2库 16表`的拆分，下面就基于此结构进行增删改操作**

#### 四、使用`shardingsphere-JDBC`

​	