package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Account;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0002 {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/S0002", method = RequestMethod.POST)
    public Response<String> s0002(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("valid") String valid, @RequestParam("uuuid") String uuuid, @RequestHeader("uuid") String uuid) {
        try {
            String privateKey = redisService.get(uuid, 0);
            byte[] deData=RSAUtils.base64Decode(password);
            byte[] decodePass=RSAUtils.decryptByPrivateKey(deData,RSAUtils.base64Decode(privateKey));
            String pass = DigestUtils.md5DigestAsHex(decodePass);
            int id = userService.register(username, pass, valid, uuuid);
            if (id > 0) {
                accountService.insertAccount(id);
                return new Response<>(HttpResponse.SUCCESS, "成功", "");
            } else if (id == 0) {
                return new Response<>(HttpResponse.ERROR, "验证码失效", null);
            } else if (id == -1) {
                return new Response<>(HttpResponse.ERROR, "验证码不正确", null);
            } else if (id == -2) {
                return new Response<>(HttpResponse.ERROR, "注册失败", null);
            } else if (id == -3) {
                return new Response<>(HttpResponse.ERROR, "账户已存在", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(HttpResponse.ERROR, "系统错误", null);
    }
}
