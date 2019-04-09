package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0012 {
    @Autowired
    private WithdrawHistoryService withdrawHistoryService;

    @RequestMapping(value = "/S0012", method = RequestMethod.POST)
    @ResponseBody
    public Response<WithdrawHistoryDto> s0012(@RequestParam("id") int id) {
        WithdrawHistoryDto dto = withdrawHistoryService.queryWithdrawHistoryById(id);
        if (dto != null) {
            return new Response<>(HttpResponse.SUCCESS, "成功", dto);
        }
        return new Response<>(HttpResponse.ERROR, "暂无数据", null);
    }
}