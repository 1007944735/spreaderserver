package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.LoginDto;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import com.sgevf.spreaderserver.utils.RSAUtils;
import com.sgevf.spreaderserver.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class S0001 {
    @Autowired
    private UserService service;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0001", method = RequestMethod.POST)
    public Response<LoginDto> s0001(@RequestParam("username") String username, @RequestParam("password") String password, @RequestHeader("uuid") String uuid) {
        try {
            User user = service.login(username);
            String privateKey = redisService.get(uuid,0);
            byte[] decodePass = RSAUtils.decryptByPrivateKey(RSAUtils.base64Decode(password), RSAUtils.base64Decode(privateKey));
            String pass=DigestUtils.md5DigestAsHex(decodePass);
            if (user == null) {
                return new Response<>(HttpResponse.ERROR, "帐号不存在", new LoginDto());
            } else if (user.getPassword().equals(pass)) {
                String token=TokenUtils.createToken(user.getId(),uuid);
                LoginDto l=new LoginDto(user.getId(),user.getUsername(),user.getNickname(),user.getHeadPortrait(),user.getPhone(),token);
                redisService.set(token,user.getId()+"",1,5,TimeUnit.HOURS);
                return new Response<>(HttpResponse.SUCCESS, "成功", l);
            } else {
                return new Response<>(HttpResponse.ERROR, "密码不正确", new LoginDto());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(HttpResponse.ERROR, "系统错误", null);
    }
}
