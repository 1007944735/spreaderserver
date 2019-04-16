package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class S0022 {
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0022", method = RequestMethod.POST)
    public Response<Map<String, String>> s0022(@RequestParam("redPacketId") String redPacketId) {
        Map<String, String> map = new HashMap<>();
        String surplus = redisService.get(redPacketId, 4);
        if (surplus != null) {
            map.put("surplus", surplus);
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } else {
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
