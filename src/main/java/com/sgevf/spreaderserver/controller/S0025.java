package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.CardService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0025 {
    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping(value = "/S0025", method = RequestMethod.POST)
    public Response<String> s0025(@RequestParam("id") int id) {
        try {
            cardService.deleteCard(id);
            return new Response<>(HttpResponse.SUCCESS, "成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
