package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.UserCardCheckModel;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.UserCardService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0028 {
    @Autowired
    private UserCardService userCardService;

    @ResponseBody
    @RequestMapping(value = "/S0028", method = RequestMethod.POST)
    public Response<UserCardCheckModel> s0028(@RequestParam("couponId") int couponId, @RequestParam("redPacketId") int redPacketId) {
        try {
            UserCardCheckModel model = userCardService.checkUserCard(couponId, redPacketId);
            return new Response<>(HttpResponse.SUCCESS, "成功", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
