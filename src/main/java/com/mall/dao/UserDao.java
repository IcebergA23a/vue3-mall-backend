package com.mall.dao;

import com.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bingshan
 * @since 2025/12/31 14:25
 */
@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> queryAll();

    @Select("select * from user where id = #{id}")
    User findUserById(int id);

}
