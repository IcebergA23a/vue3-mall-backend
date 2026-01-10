package com.usermanagement.service;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 用户认证方法，返回JWT
    public String authenticate(String username, String password) {
        User user = userRepository.selectByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return jwtUtil.generateToken(user);
        }
        throw new IllegalArgumentException("Invalid username or password");
    }

    // 根据用户名查找用户
    private User getUserByUsername(String username) {
        return userRepository.selectByUsername(username);
    }
}
