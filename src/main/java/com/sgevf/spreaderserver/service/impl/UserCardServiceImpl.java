package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.UserCardMapper;
import com.sgevf.spreaderserver.entity.UserCard;
import com.sgevf.spreaderserver.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCardServiceImpl implements UserCardService {
    @Autowired
    private UserCardMapper userCardMapper;

    @Override
    public int insertUserCards(int cardId, int userId, String getTime, String endTime,int redPacketId) {
        return userCardMapper.insertUserCards(cardId, userId, getTime, endTime,redPacketId);
    }

    @Override
    public List<UserCard> queryUserCardByUserId(int userId) {
        return userCardMapper.queryUserCardByUserId(userId);
    }
}
