package com.read.write.separate;

import com.read.write.separate.entity.T1;
import com.read.write.separate.mapper.T1Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ReadWriteSeparateApplicationTests {

    @Resource
    private T1Mapper t1Mapper;
    @Test
    void contextLoads() {
    }
    @Test
    public void insert() {
        T1 t1 = new T1();
        t1.setId(100);
        t1Mapper.save(t1);

    }
}
