package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.*;
import com.sgevf.spreaderserver.dto.CardListDto;
import com.sgevf.spreaderserver.dto.GrabResultDto;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.entity.RedPacketHistory;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.service.GrabService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.ServiceModel;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class GrabServiceImpl implements GrabService {
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedPacketHistoryMapper redPacketHistoryMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private UserCardMapper userCardMapper;

    @Transactional
    @Override
    public ServiceModel<GrabResultDto> grab(int redPacketId, int userId, String longitude, String latitude, boolean isLook) {
        GrabResultDto grabResultDto = new GrabResultDto();
        RedPacket redPacket = redPacketMapper.queryRedPacketById(redPacketId);
        long curTime = System.currentTimeMillis();
        long startTime = DateUtils.reFormat(redPacket.getStartTime());
        long endTime = DateUtils.reFormat(redPacket.getEndTime());
        Point2D a = new Point2D.Double(Double.valueOf(longitude), Double.valueOf(latitude));
        Point2D b = new Point2D.Double(Double.valueOf(redPacket.getPubLongitude()), Double.valueOf(redPacket.getPubLatitude()));
        double distance = MathUtils.getDistance(a, b);
//        if (distance > 5) {
//            //距离太远
//            return new ServiceModel<>(-1, null);
//        }
        if (!(curTime >= startTime && curTime <= endTime)) {
            //不在时间范围内
            return new ServiceModel<>(-2, null);
        }
        RedPacketHistory rph = redPacketHistoryMapper.queryHistoryByRobberIdAndRedPacketId(userId, redPacketId);
        User user = userMapper.findUserById(redPacket.getPuberId());
        grabResultDto.setName(user.getNickname());
        if (rph != null) {
            //已经抢过该红包
            grabResultDto.setMoney(rph.getRobMoney());
            return new ServiceModel<>(200, grabResultDto);
        }
        synchronized (this) {
            int num = Integer.parseInt(redisService.get(redPacket.getId() + "", 4));
            if (num == 0) {
                //红包已被抢完
                return new ServiceModel<>(-3, null);
            }
            double rMoney = Double.parseDouble(redisService.get(redPacket.getId() + "", 3));
            double money = grabing(redPacket.getType(), isLook, num, rMoney);
            //红包详情表插入数据
            RedPacketHistory history = new RedPacketHistory();
            history.setRedPacketId(redPacketId);
            history.setRobberId(userId);
            history.setRobMoney(money + "");
            redPacketHistoryMapper.insertHistory(history);
            //账户表上更新数据
            accountMapper.increaseBalance(money, userId);
            if (num == 1) {
                //更新redis 剩余红包个数
                redisService.set(redPacketId + "", num - 1 + "", 4, 10, TimeUnit.MINUTES);
                //更新redis 剩余红包金额
                redisService.set(redPacketId + "", rMoney - money + "", 3, 10, TimeUnit.MINUTES);
            } else {
                //更新redis 剩余红包个数
                redisService.set(redPacketId + "", num - 1 + "", 4);
                //更新redis 剩余红包金额
                redisService.set(redPacketId + "", rMoney - money + "", 3);
            }
            grabResultDto.setMoney(money + "");
        }
        if ("-1".equals(redPacket.getCardNum())) {
            grabResultDto.setList(new ArrayList<>());
        } else {
            List<CardListDto> listDtos = new ArrayList<>();
            String[] cardIds = redPacket.getCardNum().split(",");
            //当前时间
            String ct = DateUtils.formatCurTimeByNormal();
            for (String id : cardIds) {
                int i = (int) (Math.random() * 2);
                if (i == 0) {
                    //为0，选中
                    Card card = cardMapper.queryCardById(Integer.valueOf(id));
                    CardListDto cardListDto = new CardListDto();
                    cardListDto.setId(card.getId());
                    cardListDto.setDiscountRule(card.getDiscountRule());
                    cardListDto.setUseRule(card.getUseRule());
                    cardListDto.setStartTime(card.getStartTime());
                    cardListDto.setEffectiveStartTime(ct);
                    cardListDto.setEffectiveTime(card.getEffectiveTime());
                    cardListDto.setStatus(card.getStatus());
                    //优惠券失效时间
                    String p_et = actualEndTime(ct, redPacket.getEndTime(), card.getEffectiveTime());
                    userCardMapper.insertUserCards(card.getId(), userId, ct, p_et, redPacketId);
                    cardListDto.setEndTime(p_et);
                    listDtos.add(cardListDto);
                }
            }
            grabResultDto.setList(listDtos);
        }
        return new ServiceModel<>(200, grabResultDto);
    }

    private String actualEndTime(String curTime, String endTime, String effectiveTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date(curTime));
        Date et = DateUtils.string2Date(effectiveTime);
        if (et.getDay() != 0) {
            calendar.add(Calendar.DATE, et.getDay());
        }
        if (et.getHours() != 0) {
            calendar.add(Calendar.HOUR, et.getHours());
        }
        if (et.getMinutes() != 0) {
            calendar.add(Calendar.MINUTE, et.getMinutes());
        }
        Calendar etCalendar = Calendar.getInstance();
        etCalendar.setTime(DateUtils.string2Date(endTime));
        return calendar.compareTo(etCalendar) == -1 ? DateUtils.date2String(calendar.getTime()) : effectiveTime;
    }

    /**
     * 抢红包算法
     *
     * @return 抢到的钱
     */
    private double grabing(String type, boolean isLook, int residueNum, double residueMoney) {
        if ("1".equals(type)) {
            //是固定红包
            return residueMoney / residueNum;
        } else if ("0".equals(type)) {
            //是随机红包
            if (residueNum == 1) {
                return residueMoney;
            }
            if (isLook) {
                //看过视频广告
                double max = residueMoney - (residueNum - 1) * 0.01;
                double actual = (max - 0.01) * new Random().nextDouble() + 0.01;
                BigDecimal bd = new BigDecimal(actual);
                return bd.setScale(2, RoundingMode.DOWN).doubleValue();
            } else {
                //没看过视频广告
                double max = residueMoney - (residueNum - 1) * 0.01;
                double actual = (max - 0.01) * new Random().nextDouble() + 0.01;
                BigDecimal bd = new BigDecimal(actual);
                return bd.setScale(2, RoundingMode.DOWN).doubleValue();
            }
        }
        return 0;
    }

}
