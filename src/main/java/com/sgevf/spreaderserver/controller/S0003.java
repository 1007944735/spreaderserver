package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.ValidDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.ValidService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class S0003 {
    @Autowired
    private ValidService validService;

    @ResponseBody
    @RequestMapping(value = "/S0003", method = RequestMethod.POST)
    public Response<ValidDto> s0003() {
        Map<String, String> result = validService.getValidUrl();
        if (result == null) {
            return new Response<>(HttpResponse.ERROR, "验证码生成失败", null);
        }
        ValidDto validDto = new ValidDto(result.get("uuuid"), result.get("validUrl"));
        return new Response<>(HttpResponse.SUCCESS, "成功", validDto);
    }
}
