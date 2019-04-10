package com.sgevf.spreaderserver.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sgevf.spreaderserver.service.PayService;
import com.sgevf.spreaderserver.utils.PayUtils;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    private AlipayClient alipayClient;
    @Override
    public String pubPay(String amount, String order) {
        alipayClient = new DefaultAlipayClient(PayUtils.URL, PayUtils.APP_ID, PayUtils.APP_PRIVATE_KEY, PayUtils.FORMAT, PayUtils.CHARSET, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.SIGN_TYPE);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("发布红包消费");
        model.setSubject("发布红包消费");
        model.setOutTradeNo(order);
        model.setTimeoutExpress("15m");
        model.setTotalAmount(amount);
        model.setProductCode(PayUtils.PRODUCT_CODE);
        request.setBizModel(model);
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "";
        }
    }
}
