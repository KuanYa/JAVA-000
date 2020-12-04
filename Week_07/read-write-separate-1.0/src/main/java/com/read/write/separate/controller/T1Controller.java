package com.read.write.separate.controller;

import com.read.write.separate.annotation.DataSource;
import com.read.write.separate.annotation.DataSourceType;
import com.read.write.separate.service.T1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/readWriteSeparate")
public class T1Controller {

    @Resource
    private T1Service t1Service;

    @GetMapping("/save")
    private void save(){
        t1Service.save();
    }

    @GetMapping("/find")
    @DataSource(DataSourceType.SLAVE)
    public void find(){
        t1Service.find();
    }
}
