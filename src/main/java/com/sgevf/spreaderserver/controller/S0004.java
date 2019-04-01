package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0004 {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/S0004", method = RequestMethod.POST)
    public Response<String> s0004(@RequestParam("type") String type, @RequestParam(value = "nickname",required = false) String nickname, @RequestParam(value = "phone",required = false) String phone, @RequestParam("token") String token) {
        int size = userService.updateUser(nickname, phone, type, token);
        if (size > 0) {
            return new Response<>(HttpResponse.SUCCESS, "成功", "修改成功");
        } else {
            return new Response<>(HttpResponse.ERROR, "修改失败", "");
        }
    }
}
