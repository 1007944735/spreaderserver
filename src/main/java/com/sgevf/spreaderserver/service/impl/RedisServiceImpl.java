package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String get(String key, int index) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        factory.setDatabase(index);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String values) {
        redisTemplate.opsForValue().set(key, values, 5, TimeUnit.HOURS);
    }

    @Override
    public void set(String key, String values, int index) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        factory.setDatabase(index);
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.opsForValue().set(key, values, 5, TimeUnit.HOURS);
    }
}
