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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServicelmpl userService;

    @PostMapping
    ResponseVO<Boolean> saveUser(@RequestBody RequestVO<User> userRequestVO) {
        System.out.println(userRequestVO.getData());
        boolean save = userService.save(userRequestVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS, "save ok", save)
                : new ResponseVO<Boolean>(Status.ERROR, "save error", save);
    }

    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeUserById( @PathVariable int id) {
        boolean remove = userService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }

    @PutMapping
    ResponseVO<Boolean> updateUser(@RequestBody RequestVO<User> userRequestVO) {
        System.out.println(userRequestVO.getData());
        boolean update = userService.updateById(userRequestVO.getData());
        return update
                ? new ResponseVO<Boolean>(Status.SUCCESS, "update ok", update)
                : new ResponseVO<Boolean>(Status.ERROR, "update ok", update);
    }

    @GetMapping("/{id}")
    ResponseVO<User> getArticleById(@PathVariable int id) {
        User user = userService.getById(id);

        return user != null
                ? new ResponseVO<User>(Status.SUCCESS, "get a user", user)
                : new ResponseVO<User>(Status.ERROR, "get a user", user);
    }

    @GetMapping("/list")
    ResponseVO<List<User>> listUser() {
        List<User> usersleList = userService.list();
        return usersleList != null
                ? new ResponseVO<List<User>>(Status.SUCCESS, "list a user", usersleList)
                : new ResponseVO<List<User>>(Status.ERROR, "list a user", usersleList);
    }

}