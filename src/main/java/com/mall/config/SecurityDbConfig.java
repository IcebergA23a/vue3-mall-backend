package com.mall.config;

import com.mall.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 基于数据库的认证
 * @author bingshan
 * @since 2026/1/2 17:43
 */

@Configuration
@EnableWebSecurity
public class SecurityDbConfig {

    @Bean
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}
