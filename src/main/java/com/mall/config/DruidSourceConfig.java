package com.mall.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DruidSource配置类
 * @author bingshan
 * @since 2026/1/1 14:56
 */
@Configuration
public class DruidSourceConfig {
    /* 将配置文件中的属性与DruidDataSource对象进行绑定 */
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }
}
