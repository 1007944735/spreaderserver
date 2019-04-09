package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.AccountDto;
import com.sgevf.spreaderserver.entity.Account;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0010 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/S0010",method = RequestMethod.POST)
    @ResponseBody
    public Response<AccountDto> s0010(@RequestParam("token") String token){
        try {
            String userId = redisService.get(token, 1);
            Account account = accountService.selectAccountByUserId(Integer.valueOf(userId));
            AccountDto dto = new AccountDto();
            dto.setId(account.getId());
            dto.setBalance(account.getBalance());
            dto.setAlipayAccount(account.getAlipayAccount());
            return new Response<>(HttpResponse.SUCCESS,"成功",dto);
        }catch (Exception e){
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR,"系统错误",null);
        }
    }
}
