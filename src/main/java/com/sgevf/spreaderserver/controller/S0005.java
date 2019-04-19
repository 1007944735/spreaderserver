package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Expand;
import com.sgevf.spreaderserver.entity.RedPacket;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.OrdersService;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0005 {
    @Autowired
    private PubService pubService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0005", method = RequestMethod.POST)
    public Response<Map<String,String>> s0005(
            @RequestParam("token") String token,
            @RequestParam("type") String type,
            @RequestParam("amount") String amount,
            @RequestParam("pubLongitude") String pubLongitude,
            @RequestParam("pubLatitude") String pubLatitude,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("maxNumber") String maxNumber,
            @RequestParam("pubAddress") String pubAddress,
            @RequestParam("title") String title,
            @RequestParam("info") String info,
            @RequestParam("cardNum") String cardNum,
            @RequestParam(value = "video", required = false) MultipartFile video,
            HttpServletRequest request
    ) {
        List<MultipartFile> pictures = ((MultipartHttpServletRequest) request).getFiles("pictures");

        RedPacket redPacket = new RedPacket();
        redPacket.setAmount(Double.parseDouble(amount));
        redPacket.setType(type);
        redPacket.setPubLongitude(pubLongitude);
        redPacket.setPubLatitude(pubLatitude);
        redPacket.setStartTime(startTime);
        redPacket.setEndTime(endTime);
        redPacket.setMaxNumber(Integer.parseInt(maxNumber));
        redPacket.setPubAddress(pubAddress);
        redPacket.setCardNum(cardNum);
        redPacket.setPuberId(Integer.valueOf(redisService.get(token, 1)));

        Expand expand = new Expand();
        expand.setTitle(title);
        expand.setInfo(info);
        int id = pubService.pub(redPacket, expand, pictures, video);
        if (id > 0) {
            Map<String,String> map=new HashMap<>();
            map.put("id",""+id);
            map.put("amount",amount);
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } else {
            return new Response<>(HttpResponse.ERROR, "发布失败", null);
        }
    }
}
