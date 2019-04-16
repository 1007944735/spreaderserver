package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Account;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.AccountService;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.OrdersService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import com.sgevf.spreaderserver.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0023 {
    @Autowired
    public RedisService redisService;
    @Autowired
    public OrdersService ordersService;
    @Autowired
    public AliService aliService;
    @Autowired
    public AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/S0023", method = RequestMethod.POST)
    public Response<String> s0023(@RequestParam("redPacketId") String redPacketId, @RequestParam("isPay") String isPay, @RequestParam("orderNo") String orderNo, @RequestParam("token") String token) {
        try {
            String userId = redisService.get(token, 1);
            Account account = accountService.selectAccountByUserId(Integer.valueOf(userId));
            int result = -1;
            if ("1".equals(isPay)) {
                result = aliService.transferAccounts(UUIDUtils.getUUID(), account.getAlipayAccount(), redisService.get(redPacketId, 3));
            } else {
                result = 0;
            }
            if (result == 0) {
                redisService.delete(redPacketId, 3);
                redisService.delete(redPacketId, 4);
                ordersService.updateOrderStatus(orderNo, "-1");
                return new Response<>(HttpResponse.SUCCESS, "成功", "");
            } else {
                return new Response<>(HttpResponse.ERROR, "系统错误", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
