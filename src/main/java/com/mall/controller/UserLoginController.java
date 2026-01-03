package com.mall.controller;

import com.mall.entity.UserLoginInfo;
import com.mall.service.UserLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author bingshan
 * @since 2026/1/2 17:09
 */
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginInfoService userLoginInfoService;
    //添加
    @PostMapping("/userLogin/addUser")
    public int addUser(@RequestBody UserLoginInfo userInfo){
        return userLoginInfoService.insertUser(userInfo);
    }

    @GetMapping("/userLogin/getUser")
    public UserLoginInfo getUser(@RequestParam String username){
        return userLoginInfoService.getUserInfo(username);
    }


    @PreAuthorize("hasAnyRole('user')") // 只能user角色才能访问该方法
    @GetMapping("/userLogin/user")
    public String user(){
        return "hello,user";
    }
    @PreAuthorize("hasAnyRole('admin')") // 只能admin角色才能访问该方法
    @GetMapping("/userLogin/admin")
    public String admin(){
        return "hello,admin";
    }

    @PreAuthorize("hasAnyRole('userpw')") // 只能userpw角色才能访问该方法
    @GetMapping("/userLogin/userpw")
    public String userpw(){
        return "hello, user password";
    }

}
