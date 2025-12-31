package com.mall.controller;

import com.mall.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 Redis整合
 */
@RestController
public class CityController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testRedis")
    public String testRedis(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
       //添加字符串
        ops.set("name", "bingshan");
        String name=ops.get("name");
        System.out.println(name);

        ValueOperations opsForValue = redisTemplate.opsForValue();
        City city= new City(1, "北京", "中国");
        //添加实体类
        opsForValue.set("city", city);
        Boolean exists = redisTemplate.hasKey("city");
        System.out.println("redis是否存在相应的key：" + exists);

        //删除
        redisTemplate.delete("city");
        //更新
        redisTemplate.opsForValue().set("city", new City(2, "山西","中国"));
        //查询
        City c2 = (City) redisTemplate.opsForValue().get("city");
        System.out.println("从redis数据库中获取city：" + c2);
        return "success";
    }
}
