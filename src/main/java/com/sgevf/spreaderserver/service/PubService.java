package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.*;
import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubService {
    int pub(RedPacket redPacket, Expand expand, List<MultipartFile> pictures, MultipartFile videos);

    List<RedPacketSearchDto> searchSearch(String longitude, String latitude, String orderType, String repacketType, String[] numbers, String[] amounts);

    RedPacketDetailsDto getRedPacketDetails(Integer userId,Integer redPacketId, String longitude, String latitude);

    int updateRedPacketOrderId(Integer redPacketId,Integer orderId);

    List<HistoryReleaseDto> queryRedPacketByPuberId(Integer puberId,String type);

    List<HomeAdvertisingListDto> search();

    List<HistoryStatisicListDto> searchStatistic(Integer redPacketId);
}
