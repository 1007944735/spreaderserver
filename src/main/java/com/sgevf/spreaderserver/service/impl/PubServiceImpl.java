package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.*;
import com.sgevf.spreaderserver.dto.HistoryReleaseDto;
import com.sgevf.spreaderserver.dto.RedPacketDetailsDto;
import com.sgevf.spreaderserver.dto.RedPacketSearchDto;
import com.sgevf.spreaderserver.entity.*;
import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.MathUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private UserService userService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private RedPacketHistoryMapper redPacketHistoryMapper;

    @Autowired
    private CardMapper cardMapper;

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
        redisService.set(rp.getId() + "", rp.getAmount() + "", 3);
        redisService.set(rp.getId() + "", rp.getMaxNumber() + "", 4);
        return rp.getId();
    }

    /**
     * 根据条件搜索附近红包
     *
     * @param longitude
     * @param latitude
     * @param orderType     1 人数最多，2 金额最大，3 距离最近
     * @param redPacketType 0 随机红包,1 固定红包
     * @param numbers
     * @param amounts
     * @return
     */
    @Override
    public List<RedPacketSearchDto> searchSearch(String longitude, String latitude, String orderType, String redPacketType, String[] numbers, String[] amounts) {
        List<RedPacket> redPackets = redPacketMapper.queryRedPacketSearch(orderType, redPacketType, numbers, amounts);
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        if ("3".equals(orderType)) {
            return transform(redPackets, a, true);
        } else {
            return transform(redPackets, a, false);
        }
    }

    @Override
    public RedPacketDetailsDto getRedPacketDetails(Integer userId, Integer redPacketId, String longitude, String latitude) {
        RedPacketDetailsDto rpdd = new RedPacketDetailsDto();
        RedPacketHistory history = redPacketHistoryMapper.queryHistoryByRobberIdAndRedPacketId(userId, redPacketId);
        if (history != null) {
            rpdd.setIsGrab("1");
        } else {
            rpdd.setIsGrab("0");
        }
        RedPacket redPacket = redPacketMapper.queryRedPacketById(redPacketId);
        if("-1".equals(redPacket.getCardNum())){
            String[] cardIds=redPacket.getCardNum().split(",");
            cardMapper.queryListById(cardIds);
        }else {
            
        }
        User sponser = userService.queryUserById(redPacket.getPuberId());
        Expand expand = expandMapper.queryExpandById(redPacket.getExpandId());
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        Point2D b = new Point2D.Double(Double.valueOf(redPacket.getPubLongitude()), Double.valueOf(redPacket.getPubLatitude()));
        rpdd.setId(redPacket.getId());
        rpdd.setSponserId(sponser.getId());
        rpdd.setSponserName(sponser.getNickname());
        rpdd.setSponserImage(sponser.getHeadPortrait());
        rpdd.setAmount(redPacket.getAmount());
        rpdd.setType(redPacket.getType());
        rpdd.setPubTime(redPacket.getPubTime());
        rpdd.setPubLongitude(redPacket.getPubLongitude());
        rpdd.setPubLatitude(redPacket.getPubLatitude());
        rpdd.setStartTime(redPacket.getStartTime());
        rpdd.setEndTime(redPacket.getEndTime());
        rpdd.setMaxNumber(redPacket.getMaxNumber());
        rpdd.setPubAddress(redPacket.getPubAddress());
        rpdd.setTitle(expand.getTitle());
        rpdd.setInfo(expand.getInfo());
        double d = MathUtils.getDistance(a, b);
        BigDecimal bg = new BigDecimal(d);
        d = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        rpdd.setDistance(d);
        return rpdd;
    }

    @Override
    public int updateRedPacketOrderId(Integer redPacketId, Integer orderId) {
        return redPacketMapper.updateRedPacketOrderId(redPacketId, orderId);
    }

    @Override
    public List<HistoryReleaseDto> queryRedPacketByPuberId(Integer puberId) {
        List<HistoryReleaseDto> hrds = new ArrayList<>();
        List<RedPacket> dtos = redPacketMapper.queryRedPacketByPuberId(puberId);
        for (RedPacket redPacket : dtos) {
            HistoryReleaseDto dto = new HistoryReleaseDto();
            dto.setId(redPacket.getId());
            dto.setAmount(redPacket.getAmount() + "");
            dto.setType(redPacket.getType());
            dto.setPubTime(redPacket.getPubTime());
            dto.setPubLongitude(redPacket.getPubLongitude());
            dto.setPubLatitude(redPacket.getPubLatitude());
            dto.setStartTime(redPacket.getStartTime());
            dto.setEndTime(redPacket.getEndTime());
            dto.setMaxNumber(redPacket.getMaxNumber() + "");
            dto.setPubAddress(redPacket.getPubAddress());
            long cur = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long start = 0;
            long end = 0;
            try {
                start = sdf.parse(redPacket.getStartTime()).getTime();
                end = sdf.parse(redPacket.getEndTime()).getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
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
            Orders orders = ordersMapper.queryOrderById(redPacket.getOrderId());
            dto.setOrderNo(orders.getOrderNo());
            dto.setCreateTime(orders.getCreateTime());
            dto.setStatus(orders.getStatus());
            if ("-1".equals(orders.getStatus())) {
                dto.setActiviting("-2");
            } else {
                if (cur < start) {
                    dto.setActiviting("0");
                } else if (cur > end) {
                    dto.setActiviting("-1");
                } else {
                    dto.setActiviting("1");
                }
            }
            hrds.add(dto);
        }
        return hrds;
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
            //范围限制
//            if(d>5000){
//                continue;
//            }
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
                        if (d >= result.get(i).getDistance()) {
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
