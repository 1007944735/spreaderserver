package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.CardListDto;
import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.service.CardService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0024 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CardService cardService;


    @ResponseBody
    @RequestMapping(value = "/S0024", method = RequestMethod.POST)
    public Response<Map<String, List<CardListDto>>> s0024(@RequestParam("token") String token) {
        try {
            Map<String, List<CardListDto>> map = new HashMap<>();
            String userId = redisService.get(token, 1);
            User user = userService.queryUserById(Integer.valueOf(userId));
            if ("0".equals(user.getIsBusiness())) {
                return new Response<>(HttpResponse.ERROR, "不是商家", null);
            } else {
                Business business = businessService.queryBusinessByUserId(user.getId());
                List<Card> cards = cardService.queryCardByBusinessId(business.getId());
                List<CardListDto> dtos = new ArrayList<>();
                for (Card card : cards) {
                    CardListDto dto = new CardListDto();
                    dto.setId(card.getId());
                    dto.setDiscountRule(card.getDiscountRule());
                    dto.setUseRule(card.getUseRule());
                    dto.setStartTime(card.getStartTime());
                    dto.setEffectiveTime(card.getEffectiveTime());
                    dto.setStatus(card.getStatus());
                    dto.setSellerName(business.getbName());
                    dtos.add(dto);
                }
                map.put("list", dtos);
                return new Response<>(HttpResponse.SUCCESS, "成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
