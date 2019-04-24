package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0033 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/S0033", method = RequestMethod.POST)
    public Response<String> s0033(@RequestParam("token") String token) {
        try {
            String userId = redisService.get(token, 1);
            User user = userService.queryUserById(Integer.valueOf(userId));
            return new Response<>(HttpResponse.SUCCESS, "成功", user.getIsBusiness());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
