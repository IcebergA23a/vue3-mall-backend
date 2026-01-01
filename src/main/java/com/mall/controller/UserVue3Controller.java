package com.mall.controller;

import com.mall.entity.User;
import com.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息 测试CORS Vue3 前后端分离
 * @author bingshan
 * @since 2026/1/1 16:59
 */
@RestController
public class UserVue3Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/userVue3/queryAll")
    public List<User> queryAll(){
        List<User> lists = userService.queryAll();
        return lists;
    }

    @PostMapping("/userVue3/addUser")
    public Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/userVue3/deleteUser")
    public Integer deleteUser(@RequestParam int id) {
        return userService.deleteUser(id);
    }
}
