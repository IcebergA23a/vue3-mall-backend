package com.usermanagement.service;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 获取所有用户
    public List<User> findAllUsers() {
        return userRepository.selectList(null);
    }

    // 通过ID查找用户
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.selectById(id));
    }

    // 添加新用户
    public User addUser(User user) {
        userRepository.insert(user);
        return user;
    }

    // 更新用户信息
    public User updateUser(User user) {
        userRepository.updateById(user);
        return user;
    }

    // 通过ID删除用户
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 根据用户名查找用户
    public User findByUsername(String username) {
        return userRepository.selectByUsername(username);
    }
}
