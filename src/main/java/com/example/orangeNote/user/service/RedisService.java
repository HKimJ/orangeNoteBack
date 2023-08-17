package com.example.orangeNote.user.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    protected final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeDataInRedis(String email, String verifyCode, int expireTime) {
        redisTemplate.opsForValue().set(email, verifyCode, expireTime, TimeUnit.MINUTES);
    }

    public String retrieveDataFromRedis(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}
