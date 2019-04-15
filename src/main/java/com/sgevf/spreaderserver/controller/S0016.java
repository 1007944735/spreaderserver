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
public class S0016 {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0016", method = RequestMethod.POST)
    public Response<AccountDto> s0016(@RequestParam("token") String token) {
        try {
            String id = redisService.get(token, 1);
            Account account = accountService.selectAccountByUserId(Integer.valueOf(id));
            AccountDto dto = new AccountDto();
            dto.setId(account.getId());
            dto.setAlipayAccount(account.getAlipayAccount());
            dto.setBalance(account.getBalance());
            dto.setAlipayHead(account.getAlipayHead());
            dto.setAlipayName(account.getAlipayName());
            if ("".equals(account.getAlipayAccount()) || account.getAlipayAccount() == null) {
                dto.setIsGrant("0");
            } else {
                dto.setIsGrant("1");
            }
            return new Response<>(HttpResponse.SUCCESS, "成功", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
