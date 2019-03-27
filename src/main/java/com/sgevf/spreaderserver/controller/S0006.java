package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.RedPacketSelectDto;
import com.sgevf.spreaderserver.entity.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class S0006 {


    @ResponseBody
    @RequestMapping(value = "/S0006", method = RequestMethod.POST)
    public Response<List<RedPacketSelectDto>> s0006(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude) {
        return null;
    }
}
