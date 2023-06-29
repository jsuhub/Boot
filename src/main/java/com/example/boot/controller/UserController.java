package com.example.boot.controller;

import com.example.boot.constant.Status;
import com.example.boot.pojo.entity.User;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.impl.UserServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServicelmpl userService;

    /**
     * 保存一名用户
     * @param userRequestVO 请求数据中包含user实体
     * @return  返回成功/失败
     */
    @PostMapping
    ResponseVO<Boolean> saveUser(@RequestBody RequestVO<User> userRequestVO) {
        System.out.println(userRequestVO.getData());
        boolean save = userService.save(userRequestVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS, "save ok", save)
                : new ResponseVO<Boolean>(Status.ERROR, "save error", save);
    }

    /**
     * 删除一名用户
     * @param id 用户Id
     * @return 成功/失败
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeUserById( @PathVariable int id) {
        boolean remove = userService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }

    /**
     * 更改用户信息
     * @param userRequestVO 请求体中date中包含User
     * @return 返回体中包含Boolean
     */
    @PutMapping
    ResponseVO<Boolean> updateUser(@RequestBody RequestVO<User> userRequestVO) {
        System.out.println(userRequestVO.getData());
        boolean update = userService.updateById(userRequestVO.getData());
        return update
                ? new ResponseVO<Boolean>(Status.SUCCESS, "update ok", update)
                : new ResponseVO<Boolean>(Status.ERROR, "update ok", update);
    }

    /**
     * 展现一名用户的信息
     * @param id 用户id
     * @return 返回体包含成功/失败
     */
    @GetMapping("/{id}")
    ResponseVO<User> getArticleById(@PathVariable int id) {
        User user = userService.getById(id);
        return user != null
                ? new ResponseVO<User>(Status.SUCCESS, "get a user", user)
                : new ResponseVO<User>(Status.ERROR, "get a user", user);
    }

    /**
     * 展示一系列用户信息
     * @return  返回date中包含一系列用户信息
     *
     */
    @GetMapping("/list")
    ResponseVO<List<User>> listUser() {
        List<User> usersleList = userService.list();
        return usersleList != null
                ? new ResponseVO<List<User>>(Status.SUCCESS, "list a user", usersleList)
                : new ResponseVO<List<User>>(Status.ERROR, "list a user", usersleList);
    }

    /**
     * 用户实现登录
     * @param userRequestVO 请求体中包含User ,User中需要账号和密码
     * @return  返回体中成功/失败
     */
    @PostMapping("/login")
    ResponseVO<Boolean> login(@RequestBody RequestVO<User> userRequestVO) {
        User user = userRequestVO.getData();
        System.out.println(user);
        String username = user.getUsername();
        String password = user.getPassword();
        Boolean userByUsername = userService.getUserByUsername(username, password);
        return userByUsername
                ? new ResponseVO<>(Status.SUCCESS, "login success", userByUsername)
                : new ResponseVO<>(Status.ERROR, "login failed", userByUsername);
    }

    /**
     * 用户注册
     * @param userRequestVO 请求体中包含一个用户全部请求
     * @return 返回体中成功/失败
     */
    @PostMapping("/register")
    ResponseVO<Boolean> register(@RequestBody RequestVO<User> userRequestVO) {
        User user = userRequestVO.getData();
        Boolean usernameByUsername = userService.getUsernameByUsername(user);
        return usernameByUsername
                ? new ResponseVO<>(Status.SUCCESS, "register ok", usernameByUsername)
                : new ResponseVO<>(Status.ERROR, "register failed", usernameByUsername);
    }

    /**
     * 用户注销
     * @param userRequestVO 请求体中包含用户Id
     * @return 返回体中含有成功/失败
     */
    @PostMapping("/logout")
    ResponseVO<Boolean> logout(@RequestBody RequestVO<String> userRequestVO) {
        String data = userRequestVO.getData();
        Boolean remoteUserByUsername = userService.remoteUserByUsername(data);
        return remoteUserByUsername
                ? new ResponseVO<>(Status.SUCCESS, "logout ok", remoteUserByUsername)
                : new ResponseVO<>(Status.ERROR, "logout failed", remoteUserByUsername);
    }

}

