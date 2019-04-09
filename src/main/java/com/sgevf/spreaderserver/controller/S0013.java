package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.AccountRedPacketDetailsDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.GrabHistoryService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0013 {
    @Autowired
    private GrabHistoryService grabHistoryService;

    @ResponseBody
    @RequestMapping(value = "/S0013", method = RequestMethod.POST)
    public Response<AccountRedPacketDetailsDto> s0013(@RequestParam("id") int id) {
        try {
            AccountRedPacketDetailsDto dto = grabHistoryService.queryRedPacketHistoryById(id);
            return new Response<>(HttpResponse.SUCCESS, "成功", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
