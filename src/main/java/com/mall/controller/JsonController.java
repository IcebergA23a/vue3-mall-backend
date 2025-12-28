package com.mall.controller;

import com.mall.entity.User;
import com.mall.util.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/user")
    public ResultModel<User> getUser() {
        User user = new User(1, "冰山", "123456", "陕西");
        return new ResultModel<>(user);
    }

    @RequestMapping("/list")
    public ResultModel<List<User>> getUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "冰山", "123456", "陕西");
        User user2 = new User(2, "贝拉", "123456", "北京");
        userList.add(user1);
        userList.add(user2);
        return new ResultModel<>(userList, "获取用户列表成功");
    }

    @RequestMapping("/map")
    public ResultModel<Map<String, Object>> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        User user = new User(1, "冰山", "123456", "北京");
        map.put("作者信息", user);
        map.put("博客地址", "https://blog.csdn.net/thulium_kyg?type=blog");
        map.put("公众号", "冰山没有");
        map.put("Github", "IcebergA23a");
        return new ResultModel<>(map);
    }
}