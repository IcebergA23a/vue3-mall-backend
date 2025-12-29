package com.mall.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * 使用 SimpleMappingExceptionResolver 处理全局异常
 */
@Configuration
public class Global1Exception {
    private static final Logger log = LoggerFactory.getLogger(Global1Exception.class);

    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        /*
         * 参数一：异常的类型，注意必须是异常类型的全名
         * 参数二：视图名称
         */
         mappings.put("java.lang.ArrayIndexOutOfBoundsException", "error");

        //设置异常与视图映射信息的
        resolver.setExceptionMappings(mappings);

        return resolver;
    }

}
