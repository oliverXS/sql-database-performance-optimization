package com.oliver.controller;

import com.oliver.common.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaorui
 */
@RestController
@RequestMapping(value = "/string")
public class RedisStringController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @PutMapping(value = "/put")
    public void put(String key, @RequestParam(required = false, defaultValue = "default") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @GetMapping(value = "/get")
    public Object get(String key) {
        return redisUtils.get(key);
        // return stringRedisTemplate.opsForValue().get(key);
    }

    @DeleteMapping(value = "/del")
    public Object delete(String key) {
        return redisUtils.set(key,"", 0L, TimeUnit.SECONDS);
        // return stringRedisTemplate.opsForValue().get(key);
    }
}
