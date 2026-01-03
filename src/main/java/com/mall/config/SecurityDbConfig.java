package com.mall.config;

import com.mall.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 基于数据库的认证
 * @author bingshan
 * @since 2026/1/2 17:43
 */

@Configuration
@EnableWebSecurity
//开启方法级安全验证
@EnableMethodSecurity
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

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 允许post请求/userLogin/addUser，而无需认证
                        .requestMatchers(HttpMethod.POST,"/userLogin/addUser").permitAll()
                        .anyRequest().authenticated()) // 其他路径不需要认证
                .formLogin(form ->
                        form.loginProcessingUrl("/login")
                                .permitAll())  // 访问“/login”接口不需要进行身份认证了，防止重定向死循环
                .csrf(csrf -> csrf.disable()); // 关闭csrf, 在实际开发中开启，需要前端配合传递其他参数
        return http.build();
    }
}
