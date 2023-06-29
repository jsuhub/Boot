package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.UserMapper;
import com.example.boot.pojo.entity.User;
import com.example.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名确认密码
     * @param username 用户名
     * @param passwd  密码
     * @return TRUE/FLASE
     */
    @Override
    public Boolean getUserByUsername(String username, String passwd) {
        String dbPasswd = userMapper.selectUserPasswdByUsername(username);
        if (passwd.equals(dbPasswd)) {
            return true;
        }
        return false;
    }

    /**
     * 通过用户名确定是否存在用户
     * @param user 用户
     * @return TRUE/FALSE
     */
    @Override
    public Boolean getUsernameByUsername(User user) {
        return userMapper.selectUsernameByUsername(user.getUsername()) == null
                ? userMapper.insert(user) == 1
                : false;
    }

    /**
     * 通过用户名删除用户
     * @param username 用户名称
     * @return TRUE/FALSE
     */
    @Override
    public Boolean remoteUserByUsername(String username) {
       return userMapper.removeUserByUsername(username);

    }
}
