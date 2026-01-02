package com.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Security 认证、授权
 * @author bingshan
 * @since 2026/1/1 19:27
 */
@RestController
public class SecurityUserController {

    /**
     * 测试 Spring Security 拦截登录
     * @return
     */
    @RequestMapping("/hello")
    public String home() {
        return "Hello ,spring security!";
    }

    /**
     * 测试 Spring Security 角色访问控制
     * @return
     */
    @RequestMapping("/admin/hello")
    public String hello() {
        return "admin,Hello !";
    }

    @RequestMapping("/owner/hello")
    public String userHello() {
        return "owner,Hello !";
    }
}
