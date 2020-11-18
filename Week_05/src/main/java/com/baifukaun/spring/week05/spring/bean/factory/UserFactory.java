package com.baifukaun.spring.week05.spring.bean.factory;

import io.kimmking.bean.initialization.User;

/**
 * {@link User} 工厂类
 *
 * @since
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
