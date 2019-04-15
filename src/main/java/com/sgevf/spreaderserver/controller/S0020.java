package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.TransQueryDto;
import com.sgevf.spreaderserver.dto.WithdrawHistoryDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0020 {
    @Autowired
    private AliService aliService;
    @Autowired
    private WithdrawHistoryService withdrawHistoryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/S0020", method = RequestMethod.POST)
    public Response<TransQueryDto> s0020(@RequestParam("outBizNo") String outBizNo, @RequestParam("token") String token) {
        TransQueryDto result = aliService.queryTrans(outBizNo);
        WithdrawHistoryDto dto = withdrawHistoryService.queryWithdrawHistoryByOrder(outBizNo);
        String userId = redisService.get(token, 1);
        if (result != null) {
            if ("SUCCESS".equals(result.getStatus())) {
                accountService.reduceBalance(Double.valueOf(dto.getMoney()), Integer.valueOf(userId));
                withdrawHistoryService.updateStatus(outBizNo, "1", "");
            } else if ("FAIL".equals(result.getStatus())) {
                withdrawHistoryService.updateStatus(outBizNo, "-1", result.getFailReason());
            } else {
                withdrawHistoryService.updateStatus(outBizNo, "0", "");
            }
            return new Response<>(HttpResponse.SUCCESS, "成功", result);
        } else {
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }

    }
}
