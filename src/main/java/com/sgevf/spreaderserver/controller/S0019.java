package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.TransQueryDto;
import com.sgevf.spreaderserver.entity.WithdrawHistory;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class S0019 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AliService aliService;
    @Autowired
    private WithdrawHistoryService withdrawHistoryService;
    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/S0019", method = RequestMethod.POST)
    public Response<TransQueryDto> s0019(@RequestParam("count") Double count, @RequestParam("alipayAccount") String alipayAccount, @RequestParam("token") String token) {
        String userId = redisService.get(token, 1);
        String order = "WD" + DateUtils.formatCurTime() + userId;
        WithdrawHistory history = new WithdrawHistory();
        history.setTakerId(Integer.valueOf(userId));
        history.setMoney(count + "");
        history.setWithdrawOrder(order);
        withdrawHistoryService.insertWithdrawHistory(history);
        int status = aliService.transferAccounts(order, alipayAccount, count + "");
        if (status == 0) {
            TransQueryDto result = aliService.queryTrans(order);
            if (result != null) {
                result.setWithdrawId(history.getId());
                if ("SUCCESS".equals(result.getStatus())) {
                    accountService.reduceBalance(count, Integer.valueOf(userId));
                    withdrawHistoryService.updateStatus(order, "1", "");
                } else if ("FAIL".equals(result.getStatus())) {
                    withdrawHistoryService.updateStatus(order, "-1", result.getFailReason());
                } else {
                    withdrawHistoryService.updateStatus(order, "0", "");
                }
                return new Response<>(HttpResponse.SUCCESS, "成功", result);
            } else {
                return new Response<>(HttpResponse.ERROR, "系统错误", null);
            }
        } else {
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
