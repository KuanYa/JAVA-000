package com.baifukaun.spring.week05.spring.bean.factory;

import com.baifukaun.spring.week05.spring.bean.factory.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser("factoryBean 装配",18);
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
