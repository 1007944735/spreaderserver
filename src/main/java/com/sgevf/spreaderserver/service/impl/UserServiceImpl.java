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

    /**
     * 登录
     * @param username
     * @return
     */
    @Override
    public User login(String username) {
        return mapper.findUserByUsername(username);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param valid
     * @param uuuid
     * @return
     */
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

    /**
     * 更新用户个人信息
     * @param nickname
     * @param phone
     * @param type
     * @param token
     * @return
     */
    @Override
    public int updateUser(String nickname, String phone, String type, String token) {
        int id= Integer.parseInt(redisService.get(token,1));
        if("1".equals(type)){
            return mapper.updateUserByNickname(nickname,id);
        }else if("2".equals(type)){
            return mapper.updateUserByPhone(phone,id);
        }
        return 0;
    }
}
