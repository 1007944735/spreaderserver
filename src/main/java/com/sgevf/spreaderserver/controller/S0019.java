package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Account;
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
    public Response<Map<String, String>> s0019(@RequestParam("count") Double count, @RequestParam("alipayAccount") String alipayAccount, @RequestParam("token") String token) {
        String userId = redisService.get(token, 1);
        String order = "WD" + DateUtils.formatCurTime() + userId;
        WithdrawHistory history = new WithdrawHistory();
        history.setTakerId(Integer.valueOf(userId));
        history.setMoney(count + "");
        history.setWithdrawOrder(order);
        int id = withdrawHistoryService.insertWithdrawHistory(history);
        int status = aliService.transferAccounts(order, alipayAccount, count + "",history.getId());
        if (status != -2) {
            Map<String, String> map = new HashMap<>();
            map.put("status", status + "");
            accountService.reduceBalance(count,Integer.valueOf(userId));
            return new Response<>(HttpResponse.SUCCESS, "成功", map);
        } else {
            return new Response<>(HttpResponse.ERROR, "转账失败", null);
        }
    }
}
