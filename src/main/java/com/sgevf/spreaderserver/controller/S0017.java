package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class S0017 {
    @Autowired
    private AliService aliService;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0017", method = RequestMethod.POST)
    public Response<Map<String, String>> s0017(@RequestParam("token") String token) {
        String userId = redisService.get(token, 1);
        String authInfo = aliService.getAlipayAuthInfo(userId);
        if (!authInfo.isEmpty()) {
            Map<String, String> map = new HashMap<>();
//            map.put("url", "https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm?app_id=2016092800616091&scope=auth_user&redirect_uri=http://47.103.8.72:8080/spreader/S0018&state="+userId);
            map.put("authInfo",authInfo);
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } else {
            return new Response<>(HttpResponse.ERROR, "生成authInfo错误", null);
        }
    }
}
