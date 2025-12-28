package com.mall.controller;

import com.mall.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Desc 用户
 * @Author bingshan
 * @Date 2025/12/28 18:51
 */
@Controller
public class UserController {

    @GetMapping("/index")
    public String index(Model model) {
        List<User> userList =  List.of(
                new User(1, "alice", "alice123", "陕西"),
                new User(2, "bob", "bob123", "北京"),
                new User(3, "charlie", "charlie123", "上海")
        );
        model.addAttribute("list", userList);
        return "index";
    }
}
