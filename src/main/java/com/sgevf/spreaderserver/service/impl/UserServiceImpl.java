package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.UserMapper;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private RedisService redisService;

    @Override
    public User login(String username) {
        return mapper.findUserByUsername(username);
    }

    @Override
    public int register(String username, String password, String valid, String uuuid) {
        User user = mapper.findUserByUsername(username);
        if (user != null)
            return -3;//账户已存在
        String mCode = redisService.get(uuuid, 2);
        if (mCode == null)
            return 0;//验证码失效
        if (mCode.equals(valid)) {
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            u.setNickname(username);
            mapper.insertUser(u);
            if (u.getId() > 0)
                return u.getId();
            return -2;//注册失败
        } else {
            return -1;//验证码不正确
        }
    }
}
