package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.AccountDetailListDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.WalletAccountService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0011 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private WalletAccountService walletAccountService;

    @RequestMapping(value = "/S0011", method = RequestMethod.POST)
    @ResponseBody
    public Response<Map<String,List<AccountDetailListDto>>> s0011(@RequestParam("token") String token) {
        try {
            String userId = redisService.get(token, 1);
            List<AccountDetailListDto> lists=walletAccountService.queryWalletAccountList(Integer.valueOf(userId));
            Map<String,List<AccountDetailListDto>> map=new HashMap<>();
            map.put("list",lists);
            return new Response<>(HttpResponse.SUCCESS, "成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
