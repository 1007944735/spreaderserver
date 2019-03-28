package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.GrabResultDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.GrabService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.ServiceModel;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0009 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private GrabService grabService;

    @ResponseBody
    @RequestMapping(value = "/S0009", method = RequestMethod.POST)
    public Response<GrabResultDto> grabRadPacket(@RequestParam("redPacketId") int redPacketId, @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam("token") String token, @RequestParam("isLook") boolean isLook) {
        int userId = Integer.parseInt(redisService.get(token, 1));
        ServiceModel<GrabResultDto> results = grabService.grab(redPacketId, userId, longitude, latitude, isLook);
        switch (results.code) {
            case -1:
                return new Response<>(HttpResponse.ERROR, "距离太远", null);
            case -2:
                return new Response<>(HttpResponse.ERROR, "不在时间范围内", null);
            case -3:
                return new Response<>(HttpResponse.ERROR, "红包已被抢完", null);
            case 200:
                return new Response<>(HttpResponse.SUCCESS, "成功", results.t);
        }
        return new Response<>(HttpResponse.ERROR,"系统错误",null);
    }
}
