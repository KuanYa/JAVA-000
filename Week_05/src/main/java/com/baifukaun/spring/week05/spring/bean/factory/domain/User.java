package com.baifukaun.spring.week05.spring.bean.factory.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "user-by-component")
public class User {

    private Integer id;
    private String username;
    private Integer age;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public User(){}

    @Autowired
    public static User createUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("baifukuan");
        return user;
    }
    public static User createUser(String username,Integer age){
        User user = new User();
        user.setAge(age);
        user.setUsername(username);
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
