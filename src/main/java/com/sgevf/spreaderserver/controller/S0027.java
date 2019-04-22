package com.sgevf.spreaderserver.controller;

import com.alipay.api.domain.InteligentGeneralMerchantPromo;
import com.sgevf.spreaderserver.dto.UserCouponCardsDto;
import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.entity.UserCard;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.service.CardService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserCardService;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.junit.internal.runners.ErrorReportingRunner;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class S0027 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private CardService cardService;
    @Autowired
    private BusinessService businessService;

    @ResponseBody
    @RequestMapping(value = "/S0027", method = RequestMethod.POST)
    public Response<Map<String, List<UserCouponCardsDto>>> s0027(@RequestParam("token") String token) {
        try {
            Map<String, List<UserCouponCardsDto>> result = new HashMap<>();
            Map<Integer, UserCouponCardsDto> box = new HashMap<>();
            String userId = redisService.get(token, 1);
            List<UserCard> cards = userCardService.queryUserCardByUserId(Integer.valueOf(userId));
            for (UserCard card : cards) {
                Card c = cardService.queryCardById(card.getId());
                if (compare(card.getEndTime()) != -1 && "0".equals(card.getIsUse())) {
                    if (!box.containsKey(c.getBusinessId())) {
                        Business business = businessService.queryBusinessById(c.getBusinessId());
                        UserCouponCardsDto dto = new UserCouponCardsDto();
                        dto.setSellerId(business.getId());
                        dto.setSellerName(business.getbName());
                        dto.setSellerLogo(business.getbLogo());
                        List<UserCouponCardsDto.Coupon> coupons = new ArrayList<>();
                        UserCouponCardsDto.Coupon coupon = new UserCouponCardsDto.Coupon();
                        coupon.setId(c.getId());
                        coupon.setGetTime(card.getGetTime());
                        coupon.setEndTime(card.getEndTime());
                        coupon.setDiscountRule(c.getDiscountRule());
                        coupon.setUseRule(c.getUseRule());
                        coupon.setRedPacketId(card.getRedPacketId());
                        coupons.add(coupon);
                        dto.setCards(coupons);
                        box.put(business.getId(), dto);
                    } else {
                        UserCouponCardsDto dto = box.get(c.getBusinessId());
                        UserCouponCardsDto.Coupon coupon = new UserCouponCardsDto.Coupon();
                        coupon.setId(c.getId());
                        coupon.setGetTime(card.getGetTime());
                        coupon.setEndTime(card.getEndTime());
                        coupon.setDiscountRule(c.getDiscountRule());
                        coupon.setUseRule(c.getUseRule());
                        coupon.setRedPacketId(card.getRedPacketId());
                        dto.getCards().add(coupon);
                    }
                }
            }
            List<UserCouponCardsDto> list = new ArrayList<>();
            for (Integer key : box.keySet()) {
                list.add(box.get(key));
            }
            result.put("list", list);
            return new Response<>(HttpResponse.SUCCESS, "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }

    private int compare(String endTime) {
        Calendar ctc = Calendar.getInstance();
        ctc.setTime(DateUtils.string2Date(endTime));
        return ctc.compareTo(Calendar.getInstance());
    }
}
