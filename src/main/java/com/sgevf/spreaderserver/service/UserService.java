package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.User;

public interface UserService {
    User queryUserById(Integer id);

    User login(String username);

    int register(String username, String password, String valid, String uuuid);

    int updateUser(String nickname, String phone, String type, String token);

    int updatePassword(int id,String password);

    int updateIsBusiness(int id,String isBusiness);
}
