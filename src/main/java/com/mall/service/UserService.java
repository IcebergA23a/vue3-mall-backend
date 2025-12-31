package com.mall.service;

import com.mall.dao.UserDao;
import com.mall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author bingshan
 * @since 2025/12/31 14:29
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<User> queryAll() {
        return userDao.queryAll();
    }

    /**
     * 获取用户策略：先从缓存中获取用户，没有则取数据表中 数据，再将数据写入缓存
     */
    public User findUserById(int id) {
        String key = "user_" + id;

        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            User user = operations.get(key);
            System.out.println("从缓存中获得数据：" + user.getName());
            System.out.println("------------------------------------");
            return user;
        } else {
            User user = userDao.findUserById(id);
            System.out.println("查询数据库获得数据：" + user.getName());
            System.out.println("------------------------------------");

            // 写入缓存
            operations.set(key, user, 1, TimeUnit.MINUTES);
            return user;
        }
    }
}
