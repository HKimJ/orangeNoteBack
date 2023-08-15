package com.example.orangeNote.user.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    protected final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeDataInRedis() {
        redisTemplate.opsForValue().set("key", "value");
    }

    public String retrieveDataFromRedis() {
        return redisTemplate.opsForValue().get("key");
    }
}
