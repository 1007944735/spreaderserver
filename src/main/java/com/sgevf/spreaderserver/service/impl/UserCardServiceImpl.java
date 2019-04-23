package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.CardMapper;
import com.sgevf.spreaderserver.dao.RedPacketMapper;
import com.sgevf.spreaderserver.dao.UserCardMapper;
import com.sgevf.spreaderserver.dto.UserCardCheckModel;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.entity.UserCard;
import com.sgevf.spreaderserver.service.UserCardService;
import com.sgevf.spreaderserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UserCardServiceImpl implements UserCardService {
    @Autowired
    private UserCardMapper userCardMapper;

    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private CardMapper cardMapper;

    @Override
    public int insertUserCards(int cardId, int userId, String getTime, String endTime, int redPacketId) {
        return userCardMapper.insertUserCards(cardId, userId, getTime, endTime, redPacketId);
    }

    @Override
    public List<UserCard> queryUserCardByUserId(int userId) {
        return userCardMapper.queryUserCardByUserId(userId);
    }

    @Override
    public int updateIsUse(int id) {
        return userCardMapper.updateIsUse(id);
    }

    @Override
    public UserCard queryUserCardById(int id) {
        return userCardMapper.queryUserCardById(id);
    }

    @Override
    public UserCardCheckModel checkUserCard(int couponId, int redPacketId) {
        UserCardCheckModel result = new UserCardCheckModel();
        UserCard userCard = queryUserCardById(couponId);
        RedPacket redPacket = redPacketMapper.queryRedPacketById(redPacketId);
        if ("1".equals(userCard.getIsUse())) {
            result.setType(-1);
            return result;//-1 优惠券已使用
        }
        if ("-1".equals(redPacket.getCardNum())) {
            result.setType(-2);
            return result;//-2 无优惠券使用
        }
        if (!redPacket.getCardNum().contains(userCard.getCardId())) {
            result.setType(-3);
            return result;//-3 该优惠券不能使用
        }
        Calendar gt = Calendar.getInstance();
        gt.setTime(DateUtils.string2Date(userCard.getGetTime()));
        Calendar et = Calendar.getInstance();
        et.setTime(DateUtils.string2Date(userCard.getEndTime()));
        Calendar ct = Calendar.getInstance();
        if (ct.compareTo(gt) == -1) {
            result.setType(-4);
            return result;//未到优惠时间
        }
        if (ct.compareTo(et) == 1) {
            result.setType(-5);
            return result;//优惠券过期
        }
        Card card = cardMapper.queryCardById(Integer.valueOf(userCard.getCardId()));
        result.setType(0);
        result.setCardId(card.getId());
        result.setDiscountRule(card.getDiscountRule());
        result.setUseRule(card.getUseRule());
        result.setStatus(card.getStatus());
        result.setGetTime(userCard.getGetTime());
        result.setEndTime(userCard.getEndTime());
        return result;
    }
}
