package com.sgevf.spreaderserver.service;

public interface RedisService {
    String get(String key);

    String get(String key, int index);

    void set(String key, String values);

    void set(String key, String values, int index);
}
