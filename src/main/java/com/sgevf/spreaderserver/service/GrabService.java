package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.GrabResultDto;

public interface GrabService{
    ServiceModel<GrabResultDto> grab(int redPacketId,int userId,String longitude,String latitude,boolean isLook);
}
