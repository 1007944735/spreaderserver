package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class V0002 {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/V0002", method = RequestMethod.GET)
    @ResponseBody
    public Response<String> v0002(@RequestParam("userId") int userId, @RequestParam("status") String status) {
        try {
            userService.updateIsBusiness(userId, status);
            return new Response<>(HttpResponse.SUCCESS, "成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
