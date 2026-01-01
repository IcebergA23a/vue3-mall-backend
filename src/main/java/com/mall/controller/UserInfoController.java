package com.mall.controller;

import com.mall.entity.User;
import com.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户测试 Redis
 * @author bingshan
 * @date 2025/12/28 18:51
 */
@RestController
public class UserInfoController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/queryAll")
    public List<User> queryAll(){
        List<User> lists = userService.queryAll();
        return lists;
    }

    @GetMapping("/userInfo/findUserById")
    public Map<String, Object> findUserById(@RequestParam int id){
        User user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("name", user.getName());
        result.put("pwd", user.getPwd());
        return result;
    }


}
