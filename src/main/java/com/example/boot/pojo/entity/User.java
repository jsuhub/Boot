package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {


    String username;
    Integer id;

    @JsonProperty("passwd")
    String password;
    String email;
    String tel;
    String sex;
    String avatar;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
