package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  // 扫描@WebServlet, @WebFilter, @WebListener注解
@SpringBootApplication
public class Vue3MallBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Vue3MallBackendApplication.class, args);
    }

}
