package com.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc 启动测试类
 * @Author bingshan
 * @Date 2025/12/28 18:14
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test success";
    }
}
