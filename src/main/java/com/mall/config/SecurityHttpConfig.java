package com.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 角色访问控制
 * @author bingshan
 * @since 2026/1/1 19:38
 */
@Configuration
@EnableWebSecurity
public class SecurityHttpConfig {


    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("owner").password("owner").roles("OWNER").build());
        // 管理员用户具备 USER 和 ADMIN 角色
        manager.createUser(users.username("admin").password("admin").roles("OWNER","ADMIN").build());
        return manager;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/owner/**").hasRole("OWNER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()) // 其他路径不需要认证
                .formLogin(form ->
                        form.loginProcessingUrl("/login")
                                .permitAll())  // 访问“/login”接口不需要进行身份认证了，防止重定向死循环
                .csrf(csrf -> csrf.disable()); // 关闭csrf
        return http.build();
    }
}