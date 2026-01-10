package com.usermanagement.repository;


import com.usermanagement.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository extends BaseMapper<User> {
    // 根据用户名查找用户
    @Select("SELECT * FROM um_users WHERE username = #{username}")
    User selectByUsername(String username);


}

