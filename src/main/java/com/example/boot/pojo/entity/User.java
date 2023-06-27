package com.example.boot.pojo.entity;

import lombok.Data;

@Data
public class User {


    String username;
    Integer id;
    String password;
    String email;
    String tel;
    String sex;
    String image;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", e_main='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", sex='" + sex + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
