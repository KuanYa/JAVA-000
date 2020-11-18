package com.baifukaun.spring.week05.spring.bean.factory;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中..." + createUser());
    }

    public void initUserMethod() {
        System.out.println("自定义初始化方法 initUserMethod() : UserFactory 初始化中..." + createUser());
    }

}

