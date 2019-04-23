package com.sgevf.spreaderserver.service;


import com.sgevf.spreaderserver.dto.UserCardCheckModel;
import com.sgevf.spreaderserver.entity.UserCard;

import java.util.List;

public interface UserCardService {
    int insertUserCards(int cardId, int userId, String getTime, String endTime,int redPacketId);

    List<UserCard> queryUserCardByUserId(int userId);

    int updateIsUse(int id);

    UserCard queryUserCardById(int id);

    UserCardCheckModel checkUserCard(int couponId, int redPacketId);
}
