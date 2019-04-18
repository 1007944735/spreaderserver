package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.entity.Card;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.service.CardService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0026 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping(value = "/S0026", method = RequestMethod.POST)
    public Response<String> s0026(@RequestParam("discountRule") String discountRule, @RequestParam("useRule") String useRule, @RequestParam("startTime") String startTime, @RequestParam("effectiveTime") String endTime, @RequestParam("token") String token) {
        try {
            String userId = redisService.get(token, 1);
            Business business = businessService.queryBusinessByUserId(Integer.valueOf(userId));
            Card card = new Card();
            card.setDiscountRule(discountRule);
            card.setUseRule(useRule);
            card.setBusinessId(business.getId());
            card.setStartTime(startTime);
            card.setEffectiveTime(endTime);
            int id = cardService.insertCard(card);
            if (id != 0) {
                return new Response<>(HttpResponse.SUCCESS, "成功", "");
            } else {
                return new Response<>(HttpResponse.ERROR, "添加失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
