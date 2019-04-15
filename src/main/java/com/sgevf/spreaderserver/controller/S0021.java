package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.HistoryReleaseDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0021 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private PubService pubService;

    @ResponseBody
    @RequestMapping(value = "/S0021", method = RequestMethod.POST)
    public Response<Map<String, List<HistoryReleaseDto>>> s0021(@RequestParam("token") String token) {
        try {
            Map<String, List<HistoryReleaseDto>> map = new HashMap<>();
            String userId = redisService.get(token, 1);
            List<HistoryReleaseDto> list = pubService.queryRedPacketByPuberId(Integer.valueOf(userId));
            map.put("list", list);
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
