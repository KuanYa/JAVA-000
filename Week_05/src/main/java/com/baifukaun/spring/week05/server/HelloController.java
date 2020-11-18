package com.baifukaun.spring.week05.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello(HttpServletRequest request){

        return "自定义请求头内容为2222-------->" + request.getHeader("nio");
    }
}
