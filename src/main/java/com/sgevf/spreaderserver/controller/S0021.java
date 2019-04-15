package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.HistoryReleaseDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0021 {
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0021", method = RequestMethod.POST)
    public Response<HistoryReleaseDto> s0021(@RequestParam("token") String token) {
        String userId = redisService.get(token, 1);
        return null;
    }

}
