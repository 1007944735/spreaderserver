package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.RedPacketDetailsDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0008 {
    @Autowired
    private PubService pubService;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0008", method = RequestMethod.POST)
    public Response<RedPacketDetailsDto> s0008(@RequestParam("redPacketId") Integer redPacketId, @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude,@RequestParam("token") String token) {
        RedPacketDetailsDto result;
        try {
            String id=redisService.get(token,1);
            result = pubService.getRedPacketDetails(Integer.valueOf(id),redPacketId, longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
        return new Response<>(HttpResponse.SUCCESS, "成功", result);
    }
}
