package com.baifukaun.spring.week05.spring.jdbc;

import com.baifukaun.spring.week05.spring.bean.factory.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 获取用户集合
     *
     * @return
     * @throws Exception
     */
    List<User> listUser() throws Exception;

    /**
     * 添加人员
     *
     * @throws Exception
     */
    void addUser()throws Exception;

    /**
     * 修改人员
     *
     * @throws Exception
     */
    void modifyUser()throws Exception;

    /**
     * 删除人员
     *
     * @throws Exception
     */
    void deleteUser() throws Exception;

}
