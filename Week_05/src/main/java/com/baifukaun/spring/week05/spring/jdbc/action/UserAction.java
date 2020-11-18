package com.baifukaun.spring.week05.spring.jdbc.action;

import io.kimmking.jdbc.impl.UserServiceImpl;

public class UserAction {
    private static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) throws Exception {
         // addUser();

        // modifyUser();

        deleteUser();

        // listUser();
    }

    /**
     * 添加人员
     *
     * @throws Exception
     */
    public static void addUser() throws Exception {
        userService.addUser();
    }

    /**
     * 修改人员
     *
     * @throws Exception
     */
    public static void modifyUser()throws Exception{
        userService.modifyUser();
    }

    /**
     * 删除人员
     *
     * @throws Exception
     */
    public static void deleteUser()throws Exception{
        userService.deleteUser();
    }

    public static void listUser()throws Exception{
        userService.listUser();
    }
}
