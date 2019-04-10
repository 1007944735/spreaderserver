package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.PayOrderDto;
import com.sgevf.spreaderserver.entity.Orders;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.OrdersService;
import com.sgevf.spreaderserver.service.PayService;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0014 {
    @Autowired
    private PayService payService;
    @Autowired
    private OrdersService ordersService;

    @ResponseBody
    @RequestMapping(value = "/S0014", method = RequestMethod.POST)
    public Response<PayOrderDto> s0014(@RequestParam("amount") String amount) {
        String order = "SGEVF" + DateUtils.formatCurTime();
        String orderString = payService.pubPay(amount, order);
        Orders o = new Orders();
        o.setMoney(amount);
        o.setOrderNo(order);
        ordersService.insertOrder(o);
        PayOrderDto dto = new PayOrderDto();
        dto.setOrderString(orderString);
        dto.setId(o.getId());
        dto.setAmount(amount);
        return new Response<>(HttpResponse.SUCCESS, "成功", dto);
    }
}