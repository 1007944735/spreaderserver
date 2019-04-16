package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.PayOrderDto;
import com.sgevf.spreaderserver.entity.Orders;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.OrdersService;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.PubService;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0014 {
    @Autowired
    private AliService aliService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private PubService pubService;

    @ResponseBody
    @RequestMapping(value = "/S0014", method = RequestMethod.POST)
    public Response<PayOrderDto> s0014(@RequestParam("amount") String amount, @RequestParam("redPacketId") String redPacketId, @RequestParam(value = "order", required = false) String order) {
        try {
            PayOrderDto dto = new PayOrderDto();
            if (order.isEmpty()) {
                order = "SGEVF" + DateUtils.formatCurTime();
                Orders o = new Orders();
                o.setMoney(amount);
                o.setOrderNo(order);
                ordersService.insertOrder(o);
                pubService.updateRedPacketOrderId(Integer.valueOf(redPacketId), o.getId());
            }
            String orderString = aliService.pubPay(amount, order);
            dto.setOrderString(orderString);
//            dto.setId(o.getId());
            dto.setAmount(amount);
            return new Response<>(HttpResponse.SUCCESS, "成功", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}