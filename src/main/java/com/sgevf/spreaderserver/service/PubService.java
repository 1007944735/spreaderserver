package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.RedPacketSearchDto;
import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubService {
    int pub(RedPacket redPacket, Expand expand, List<MultipartFile> pictures, MultipartFile videos);

    List<RedPacketSearchDto> searchDefault(String longitude, String latitude);

    List<RedPacketSearchDto> searchByOrder(String longitude, String latitude, String type);

    List<RedPacketSearchDto> searchByFilter(String longitude, String latitude, String type,String[] numbers,String[] amounts);
}
