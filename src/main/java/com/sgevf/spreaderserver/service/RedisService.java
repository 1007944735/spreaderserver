package com.sgevf.spreaderserver.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    String get(String key);

    String get(String key, int index);

    void set(String key, String values, int index);

    void set(String key, String values, int index, long l, TimeUnit timeUnit);

    void delete(String key,int index);
}
