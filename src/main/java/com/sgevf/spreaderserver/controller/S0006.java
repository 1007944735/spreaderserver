package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.RedPacketSearchDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class S0006 {

    @Autowired
    private PubService pubService;

    private List<RedPacketSearchDto> results;

    @ResponseBody
    @RequestMapping(value = "/S0006", method = RequestMethod.POST)
    public Response<List<RedPacketSearchDto>> s0006(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude) {
        try {
            results = pubService.searchDefault(longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
        return new Response<>(HttpResponse.SUCCESS, "成功", results);
    }
}
