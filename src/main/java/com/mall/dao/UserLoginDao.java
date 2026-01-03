package com.mall.dao;


import com.mall.entity.Role;
import com.mall.entity.UserLoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginDao {

    UserLoginInfo getUserByUsername(String username);

    // 添加用户
    @Insert("insert into user_login(username, password, role) value(#{username}, #{password}, #{role})")
    int insertUserInfo(UserLoginInfo userLoginInfo);

    List<Role> getRolesByUserId(int id);
}
