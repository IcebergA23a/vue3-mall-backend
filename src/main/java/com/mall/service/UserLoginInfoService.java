package com.mall.service;

import com.mall.dao.UserLoginDao;
import com.mall.entity.UserLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author bingshan
 * @since 2026/1/2 17:07
 */
@Service
public class UserLoginInfoService {
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public int insertUser(UserLoginInfo userInfo){
        return userLoginDao.insertUserInfo(userInfo);
    }

    public UserLoginInfo getUserInfo(String username){
        return userLoginDao.getUserByUsername(username);
    }

}
