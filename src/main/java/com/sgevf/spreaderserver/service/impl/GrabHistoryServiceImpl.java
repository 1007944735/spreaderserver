package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.ExpandMapper;
import com.sgevf.spreaderserver.dao.RedPacketHistoryMapper;
import com.sgevf.spreaderserver.dao.RedPacketMapper;
import com.sgevf.spreaderserver.dao.UserMapper;
import com.sgevf.spreaderserver.dto.AccountRedPacketDetailsDto;
import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.entity.RedPacketHistory;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.service.GrabHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrabHistoryServiceImpl implements GrabHistoryService {
    @Autowired
    private RedPacketHistoryMapper redPacketHistoryMapper;
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private ExpandMapper expandMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public AccountRedPacketDetailsDto queryRedPacketHistoryById(int robberId) {
        AccountRedPacketDetailsDto dto = new AccountRedPacketDetailsDto();
        RedPacketHistory redPacketHistory = redPacketHistoryMapper.queryHistoryById(robberId);
        dto.setId(redPacketHistory.getId());
        dto.setRedPacketId(redPacketHistory.getRedPacketId());
        dto.setRobMoney(redPacketHistory.getRobMoney());
        dto.setRobTime(redPacketHistory.getRobTime());
        RedPacket redPacket = redPacketMapper.queryRedPacketById(redPacketHistory.getRedPacketId());
        dto.setAmount(redPacket.getAmount() + "");
        dto.setType(redPacket.getType());
        dto.setPubTime(redPacket.getPubTime());
        dto.setPubLongitude(redPacket.getPubLongitude());
        dto.setPubLatitude(redPacket.getPubLatitude());
        dto.setStartTime(redPacket.getStartTime());
        dto.setEndTime(redPacket.getEndTime());
        dto.setMaxNumber(redPacket.getMaxNumber() + "");
        dto.setPubAddress(redPacket.getPubAddress());
        dto.setExpandId(redPacket.getExpandId());
        Expand expand = expandMapper.queryExpandById(redPacket.getExpandId());
        dto.setTitle(expand.getTitle());
        dto.setInfo(expand.getInfo());
        dto.setVideoUrl(expand.getVideoUrl());
        dto.setImage1Url(expand.getImage1Url());
        dto.setImage2Url(expand.getImage2Url());
        dto.setImage3Url(expand.getImage3Url());
        dto.setImage4Url(expand.getImage4Url());
        dto.setImage5Url(expand.getImage5Url());
        dto.setImage6Url(expand.getImage6Url());
        User user = userMapper.findUserById(redPacket.getPuberId());
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNickname(user.getNickname());
        dto.setHeadPortrait(user.getHeadPortrait());
        dto.setPhone(user.getPhone());
        return dto;
    }
}
