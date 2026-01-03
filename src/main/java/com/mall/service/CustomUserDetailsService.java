package com.mall.service;

import com.mall.dao.UserLoginDao;
import com.mall.entity.UserLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingshan
 * @since 2026/1/2 17:13
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserLoginInfoService userLoginInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名从数据库获取用户信息
        UserLoginInfo userLoginInfo = userLoginInfoService.getUserInfo(username);
        if (userLoginInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        String role = userLoginInfo.getRole();
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_" + role);

        // org.springframework.security.core.userdetails.
//        User result = new User(userLoginInfo.getUsername(),
//                // 数据库是明文的，这里需要加密
//                passwordEncoder.encode(userLoginInfo.getPassword()),
//                authorities
//                );

        User result1 = new User(userLoginInfo.getUsername(),
                // 数据库存的是加密后的密码，不需要在加密
                userLoginInfo.getPassword(),
                authorities
        );
        return result1;
    }
}
