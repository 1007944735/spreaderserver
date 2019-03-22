package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.User;

public interface UserServiceImpl {
    User findUser(Integer id);

    Integer insertUser(String name, String age);
}
