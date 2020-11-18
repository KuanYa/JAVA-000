package com.baifukaun.spring.week05.spring.jdbc.impl;


import com.baifukaun.spring.week05.spring.bean.initialization.User;
import com.baifukaun.spring.week05.spring.jdbc.UserService;
import com.baifukaun.spring.week05.spring.jdbc.connection.DBConnection;
import com.baifukaun.spring.week05.spring.jdbc.connection.HikariConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static Connection connection;

    private static PreparedStatement preparedStatement;

    /**
     * 获取用户集合
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<User> listUser() throws Exception {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select id,username,age,password from user";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                user.setPassword(resultSet.getString(4));
                list.add(user);
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    /**
     * 添加人员
     *
     * @throws Exception
     */
    @Override
    public void addUser() throws Exception {
        try {
            connection = HikariConnection.connection;
            String sql = "insert into `user` (`username`, `age`, `password`) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "xiec1hunying");
            preparedStatement.setInt(2, 18);
            preparedStatement.setString(3, "098765431");
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 修改人员
     *
     * @throws Exception
     */
    @Override
    public void modifyUser() throws Exception {
        try {
            connection = DBConnection.getConnection();
            String sql = "update user set age = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 28);
            preparedStatement.setInt(2, 12);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 删除人员
     *
     * @throws Exception
     */
    @Override
    public void deleteUser() throws Exception {
        try {
            connection = DBConnection.getConnection();
            String sql = "delete from user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 12);
            preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
