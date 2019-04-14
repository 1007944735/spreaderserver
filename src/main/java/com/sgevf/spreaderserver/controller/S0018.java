package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.AlipayUserInfoDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0018 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private AliService aliService;

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/S0018", method = RequestMethod.GET)
    public Response<String> s0018(@RequestParam("state") String state, @RequestParam("auth_code") String authCode) {
//        String id = redisService.get(token, 1);
        String accessToken = aliService.getAlipayAccessToken(authCode);
        AlipayUserInfoDto result = aliService.getAliUserInfo(accessToken);
        if (result != null) {
            accountService.bindAlipayAccount(result.getUserId(), result.getAvatar(), result.getNickName(), Integer.valueOf(state));
            return new Response<>(HttpResponse.SUCCESS, "成功", "");
        } else {
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }


}
