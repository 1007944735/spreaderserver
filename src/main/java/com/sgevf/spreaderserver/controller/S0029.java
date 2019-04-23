package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.UserCardService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S0029 {
    @Autowired
    private UserCardService userCardService;

    @ResponseBody
    @RequestMapping(value = "/S0029", method = RequestMethod.POST)
    public Response<String> s0029(@Param("userCardId") int userCardId) {
        try {
            userCardService.updateIsUse(userCardId);
            return new Response<>(HttpResponse.SUCCESS, "成功", "使用成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", "");
        }
    }
}
