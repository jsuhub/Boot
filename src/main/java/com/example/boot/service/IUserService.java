package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.pojo.entity.User;

public interface IUserService extends IService<User> {

    Boolean getUserByUsername(String username, String passwd);

    Boolean getUsernameByUsername(User user);
}
