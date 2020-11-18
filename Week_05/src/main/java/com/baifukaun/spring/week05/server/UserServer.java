package com.baifukaun.spring.week05.server;


import com.baifukaun.spring.week05.spring.jdbc.connection.HikariConnection;
import com.baifukaun.spring.week05.spring.jdbc.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServer {
    private static UserServiceImpl userService = new UserServiceImpl();

    /**
     * 添加人员
     *
     * @throws Exception
     */
    @GetMapping("/api/addUser")
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
