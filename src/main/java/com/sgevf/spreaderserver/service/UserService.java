package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.User;

public interface UserService {
    User login(String username);
    int register(String username,String password,String valid,String uuuid);
}
