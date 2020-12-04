package com.read.write.separate.service.impl;

import com.read.write.separate.annotation.DataSource;
import com.read.write.separate.annotation.DataSourceType;
import com.read.write.separate.entity.T1;
import com.read.write.separate.mapper.T1Mapper;
import com.read.write.separate.service.T1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("t1Service")
public class T1ServiceImpl implements T1Service {
   @Resource
   private T1Mapper t1Mapper;

    @Override
    public void save() {
        T1 t1 = new T1();
        t1.setId(101);
        t1Mapper.save(t1);
    }

    @Override
    public void find() {
       T1 t1 =  t1Mapper.findById(100);
       log.info("结果集-------------》"+t1);
    }
}
