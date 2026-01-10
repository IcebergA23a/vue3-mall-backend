package com.usermanagement.controller;

import com.usermanagement.model.User;
import com.usermanagement.service.AuthService;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(user.getPassword());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        try {
            String token = authService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/password");
        }
    }

    // 获取用户信息
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    // 更新用户信息
    @PutMapping("/")
    public ResponseEntity<?> updateUserInfo(@RequestBody User userUpdates) {
        userService.updateUser(userUpdates);
        return ResponseEntity.ok(userUpdates);
    }

    // 获取所有用户
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
