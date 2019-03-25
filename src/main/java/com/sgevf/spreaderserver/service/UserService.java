package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.User;

public interface UserService {
    User findUser(Integer id);

    User login(String username);
}
