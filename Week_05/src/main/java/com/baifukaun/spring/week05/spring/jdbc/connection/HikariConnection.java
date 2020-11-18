package com.baifukaun.spring.week05.spring.jdbc.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
    public static  Connection connection = null;

    public static void getConnection() {
        HikariConfig config = new HikariConfig("/config/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        try {
            connection = ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
