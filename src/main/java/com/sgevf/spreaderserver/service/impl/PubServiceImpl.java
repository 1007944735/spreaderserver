package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.ExpandMapper;
import com.sgevf.spreaderserver.dao.RedPacketMapper;
import com.sgevf.spreaderserver.dto.RedPacketSearchDto;
import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PubServiceImpl implements PubService {
    @Autowired
    private FileService fileService;

    @Autowired
    private ExpandMapper expandMapper;

    @Autowired
    private RedPacketMapper redPacketMapper;

    @Autowired
    private RedisService redisService;

    private String[] urls;

    private Expand e;
    private RedPacket rp;

    /**
     * @param redPacket
     * @param expand
     * @param pictures
     * @param video
     * @return -1 发布失败
     */
    @Transactional
    @Override
    public int pub(RedPacket redPacket, Expand expand, List<MultipartFile> pictures, MultipartFile video) {
        urls = new String[6];
        e = expand;
        rp = redPacket;
        String videoUrl = fileService.uploadVideo(video);
        if (videoUrl == null) {
            return -1;
        } else if (!"".equals(videoUrl)) {
            e.setVideoUrl(videoUrl);
        }
        for (int i = 0; i < pictures.size(); i++) {
            String pictureUrl = fileService.uploadImage(pictures.get(i));
            if (!pictureUrl.isEmpty()) {
                urls[i] = pictureUrl;
            }
        }
        e.setImage1Url(urls[0]);
        e.setImage2Url(urls[1]);
        e.setImage3Url(urls[2]);
        e.setImage4Url(urls[3]);
        e.setImage5Url(urls[4]);
        e.setImage6Url(urls[5]);

        int eNum = expandMapper.insertExpand(e);
        if (eNum <= 0) {
            return -1;
        }
        rp.setExpandId(e.getId());

        int rNum = redPacketMapper.insertRedPacket(rp);
        if (rNum <= 0) {
            return -1;
        }
        redisService.set(rp.getId()+"",rp.getAmount()+"",3);
        redisService.set(rp.getId()+"",rp.getMaxNumber()+"",4);
        return rp.getId();
    }

    /**
     * 获取附近红包信息(默认方式)
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<RedPacketSearchDto> searchDefault(String longitude, String latitude) {
        List<RedPacket> redPackets = redPacketMapper.queryRedPacket();
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        return transform(redPackets, a, false);
    }

    /**
     * 获取附近红包信息
     *
     * @param longitude
     * @param latitude
     * @param type      1 人数最多 2 金额最大 3 离我最近
     * @return
     */
    @Override
    public List<RedPacketSearchDto> searchByOrder(String longitude, String latitude, String type) {
        List<RedPacket> redPackets = null;
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        List<RedPacketSearchDto> result = null;
        switch (type) {
            case "1":
                redPackets = redPacketMapper.queryRedPacketOrderByNumber();
                result = transform(redPackets, a, false);
                break;
            case "2":
                redPackets = redPacketMapper.queryRedPacketOrderByAmount();
                result = transform(redPackets, a, false);
                break;
            case "3":
                redPackets = redPacketMapper.queryRedPacket();
                result = transform(redPackets, a, true);
                break;
        }
        return result;
    }

    @Override
    public List<RedPacketSearchDto> searchByFilter(String longitude, String latitude, String type, String[] numbers, String[] amounts) {
        List<RedPacket> redPackets=redPacketMapper.queryRedPacketFilter(type,numbers,amounts);
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        return transform(redPackets, a, false);
    }


    private List<RedPacketSearchDto> transform(List<RedPacket> redPackets, Point2D dot, boolean distance) {
        List<RedPacketSearchDto> result = new ArrayList<>();
        for (RedPacket redPacket : redPackets) {
            RedPacketSearchDto searchDto = new RedPacketSearchDto();
            searchDto.setId(redPacket.getId());
            searchDto.setAmount(redPacket.getAmount());
            searchDto.setType(redPacket.getType());
            searchDto.setPubTime(redPacket.getPubTime());
            searchDto.setPubLongitude(redPacket.getPubLongitude());
            searchDto.setPubLatitude(redPacket.getPubLatitude());
            searchDto.setStartTime(redPacket.getStartTime());
            searchDto.setEndTime(redPacket.getEndTime());
            searchDto.setMaxNumber(redPacket.getMaxNumber());
            searchDto.setPubAddress(redPacket.getPubAddress());
            Point2D b = new Point2D.Double(Double.valueOf(redPacket.getPubLongitude()), Double.valueOf(redPacket.getPubLatitude()));

            double d = MathUtils.getDistance(dot, b);
            BigDecimal bg = new BigDecimal(d);
            d = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            searchDto.setDistance(d);

            Expand e = expandMapper.queryExpandById(redPacket.getExpandId());
            searchDto.setTitle(e.getTitle());
            searchDto.setInfo(e.getInfo());
            searchDto.setVideoUrl(e.getVideoUrl());
            searchDto.setImage1Url(e.getImage1Url());
            searchDto.setImage2Url(e.getImage2Url());
            searchDto.setImage3Url(e.getImage3Url());
            searchDto.setImage4Url(e.getImage4Url());
            searchDto.setImage5Url(e.getImage5Url());
            searchDto.setImage6Url(e.getImage6Url());
            if (distance) {
                if (result.isEmpty() || d < result.get(0).getDistance()) {
                    result.add(0, searchDto);
                } else {
                    for (int i = result.size() - 1; i >= 0; i--) {
                        if (d > result.get(i).getDistance()) {
                            result.add(i + 1, searchDto);
                            break;
                        }
                    }
                }
            } else {
                result.add(searchDto);
            }
        }
        return result;
    }
}