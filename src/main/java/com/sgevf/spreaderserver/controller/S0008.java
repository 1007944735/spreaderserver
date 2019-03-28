package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.RedPacketSearchDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class S0008 {
    @Autowired
    private PubService pubService;

    @ResponseBody
    @RequestMapping(value = "/S0008", method = RequestMethod.POST)
    public Response<List<RedPacketSearchDto>> s0008(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "number", required = false) String number, @RequestParam(value = "amount", required = false) String amount) {
        String[] numbers = number != null ? number.split(",") : null;
        String[] amounts = amount != null ? amount.split(",") : null;
        List<RedPacketSearchDto> result = pubService.searchByFilter(longitude, latitude, type, numbers, amounts);
        return new Response<>(HttpResponse.SUCCESS, "成功", result);
    }
}
