package com.sgevf.spreaderserver.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sgevf.spreaderserver.service.OrdersService;
import com.sgevf.spreaderserver.utils.PayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class S0015 {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/S0015", method = RequestMethod.POST)
    public void s0015(HttpServletRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            boolean flag = AlipaySignature.rsaCheckV1(params, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.CHARSET, "RSA2");
            if(flag){
                String orderNo=params.get("out_trade_no");
                ordersService.updateOrderStatus(orderNo);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
