package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.UserMapper;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;


    @Override
    public User findUser(Integer id) {
        return mapper.findUser(id);
    }

    @Override
    public User login(String username) {
        return mapper.findUserByUsername(username);
    }

}
