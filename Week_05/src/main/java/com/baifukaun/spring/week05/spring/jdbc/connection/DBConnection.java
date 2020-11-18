package io.kimmking.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) throws SQLException {
        getConnection();
    }

    /**
     * 创建数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() {
        // 设置驱动名称
        String driverClassName = "com.mysql.jdbc.Driver";
        // 执行数据库连接
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "123456";
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
