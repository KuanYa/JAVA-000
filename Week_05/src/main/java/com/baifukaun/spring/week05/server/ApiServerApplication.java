package com.baifukaun.spring.week05.server;

import com.baifukaun.spring.week05.spring.jdbc.connection.HikariConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServerApplication {

    public static void main(String[] args) {
        // 启动数据库连接池
        HikariConnection.getConnection();
        SpringApplication.run(ApiServerApplication.class, args);
    }
}
