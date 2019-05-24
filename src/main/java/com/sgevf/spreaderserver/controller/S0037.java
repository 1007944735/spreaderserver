package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.HistoryStatisicDto;
import com.sgevf.spreaderserver.dto.HistoryStatisicListDto;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class S0037{
    @Autowired
    private RedisService redisService;
    @Autowired
    private PubService pubService;
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/S0037",method = RequestMethod.POST)
    public Response<HistoryStatisicDto> s0037(@RequestParam("id") int id,@RequestParam("account") String account,@RequestParam("maxNum") String maxNum){
        HistoryStatisicDto historyStatisicDto=new HistoryStatisicDto();
        String money=redisService.get(id+"",3);
        String num=redisService.get(id+"",4);
        historyStatisicDto.money=money;
        historyStatisicDto.num=num;
        historyStatisicDto.reNum=num;
        historyStatisicDto.reMoney=money;
        List<HistoryStatisicListDto> list=pubService.searchStatistic(id);
        for(HistoryStatisicListDto dto:list){
            User user =userService.queryUserById(dto.id);
            dto.name=user.getNickname();
        }
        historyStatisicDto.list=list;
        return new Response<>(HttpResponse.SUCCESS,"成功",historyStatisicDto);
    }
}
