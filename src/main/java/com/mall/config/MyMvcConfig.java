 package com.mall.config;


 import org.springframework.boot.web.server.ConfigurableWebServerFactory;
 import org.springframework.boot.web.server.WebServerFactoryCustomizer;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 /**
  * 修改Servlet 容器配置
  */
 @Configuration
public class MyMvcConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(10000);
            }
        };
    }
}