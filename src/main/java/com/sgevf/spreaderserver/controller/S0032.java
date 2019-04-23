package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.HomeAdvertisingListDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0032 {
    @Autowired
    private PubService pubService;

    @ResponseBody
    @RequestMapping(value = "/S0032", method = RequestMethod.POST)
    public Response<Map<String, List<HomeAdvertisingListDto>>> s0032() {
        try {
            Map<String, List<HomeAdvertisingListDto>> map = new HashMap<>();
            List<HomeAdvertisingListDto> list = pubService.search();
            map.put("list", list);
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }


    }
}
