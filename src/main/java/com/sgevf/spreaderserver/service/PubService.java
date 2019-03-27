package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubService {
    int pub(RedPacket redPacket, Expand expand, List<MultipartFile> pictures, MultipartFile videos);

    void searchDefault(String longitude,String latitude);
}
