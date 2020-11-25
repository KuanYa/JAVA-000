### 作业表结构

#### 1.用户表

```sql
CREATE TABLE `user` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
 `username` varchar(32) NOT NULL COMMENT '用户名',
 `password` varchar(64) NOT NULL COMMENT '密码',
 `mobile` varchar(11) NOT NULL COMMENT '手机号码',
 `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
 `creator` bigint(20) NOT NULL COMMENT '创建人',
 `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
 `modified_by` bigint(20) NOT NULL COMMENT '修改人',
 `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
 PRIMARY KEY (`id`),
 UNIQUE KEY `un_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';
```

#### 2.商品表

```sql
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_num` varchar(128) NOT NULL COMMENT '商品编码',
  `goods_desc` varchar(255) DEFAULT '' COMMENT '商品描述',
  `goods_comm` varchar(1024) DEFAULT '' COMMENT '商品备注',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品单价',
  `picture_address` varchar(128) DEFAULT NULL COMMENT '商品图片地址',
  `is_on_sale` tinyint(1) DEFAULT '0' COMMENT '1.上架 0.下架',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `modified_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_goods_num` (`goods_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';
```

#### 3.订单表

```sql
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_num` varchar(128) NOT NULL COMMENT '订单编码',
  `order_status` int(11) NOT NULL COMMENT '订单状态',
  `logistics_status` int(11) NOT NULL COMMENT '物流状态',
  `order_amount` decimal(18,3) NOT NULL COMMENT '订单金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `modified_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
```

#### 4.商品与订单关联表

```sql
CREATE TABLE `r_goods_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `modified_by` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `goods_number` int(11) NOT NULL DEFAULT '0' COMMENT '下单数量',
  `sales_amount` decimal(18,3) DEFAULT '0.000' COMMENT '售卖价格',
  `goods_price` decimal(18,3) DEFAULT '0.000' COMMENT '商品单价',
  `snapshot_id` bigint(20) DEFAULT NULL COMMENT '订单快照id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品与订单关联表';
```

