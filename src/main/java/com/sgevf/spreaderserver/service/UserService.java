package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dao.UserMapper;
import com.sgevf.spreaderserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl{
    @Autowired
    private UserMapper mapper;


    @Override
    public User findUser(Integer id) {
        return mapper.findUser(id);
    }

    @Override
    public Integer insertUser(String name, String age) {
        return mapper.insertUser(name,age);
    }
}
