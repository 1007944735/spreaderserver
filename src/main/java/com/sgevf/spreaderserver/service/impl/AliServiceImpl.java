package com.sgevf.spreaderserver.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.sgevf.spreaderserver.dto.AlipayUserInfoDto;
import com.sgevf.spreaderserver.dto.TransQueryDto;
import com.sgevf.spreaderserver.service.AliService;
import com.sgevf.spreaderserver.service.WithdrawHistoryService;
import com.sgevf.spreaderserver.utils.PayUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class AliServiceImpl implements AliService {
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
        request.setNotifyUrl(PayUtils.BACK_URL);
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getAlipayAuthInfo(String userId) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("apiname", "com.alipay.account.auth");
        map.put("method", "alipay.open.auth.sdk.code.get");
        map.put("app_id", PayUtils.APP_ID);
        map.put("app_name", "mc");
        map.put("biz_type", "openservice");
        map.put("pid", PayUtils.BUSINESS_UID);
        map.put("product_id", "APP_FAST_LOGIN");
        map.put("scope", "kuaijie");
        map.put("target_id", userId);//用户id
        map.put("auth_type", "AUTHACCOUNT");
        map.put("sign_type", PayUtils.SIGN_TYPE);
        try {
            String signStr = AlipaySignature.getSignContent(map);
            String sign = AlipaySignature.rsa256Sign(signStr, PayUtils.APP_PRIVATE_KEY, PayUtils.CHARSET);
            return signStr + "&sign=" + URLEncoder.encode(sign, PayUtils.CHARSET);
//            return URLEncoder.encode(signStr+"$sign="+sign,"utf-8");
        } catch (AlipayApiException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getAlipayAccessToken(String authCode) {
        try {
            alipayClient = new DefaultAlipayClient(PayUtils.URL, PayUtils.APP_ID, PayUtils.APP_PRIVATE_KEY, PayUtils.FORMAT, PayUtils.CHARSET, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.SIGN_TYPE);
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
            request.setGrantType("authorization_code");
            request.setCode(authCode);
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
            return response.getAccessToken();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AlipayUserInfoDto getAliUserInfo(String accessToken) {
        try {
            alipayClient = new DefaultAlipayClient(PayUtils.URL, PayUtils.APP_ID, PayUtils.APP_PRIVATE_KEY, PayUtils.FORMAT, PayUtils.CHARSET, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.SIGN_TYPE);
            AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);
            if (response.isSuccess()) {
                AlipayUserInfoDto dto = new AlipayUserInfoDto();
                dto.setUserId(response.getUserId());
                dto.setAvatar(response.getAvatar());
                dto.setNickName(response.getNickName());
                return dto;
            } else {
                return null;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int transferAccounts(String order, String payeeAccount, String amount) {
        try {
            alipayClient = new DefaultAlipayClient(PayUtils.URL, PayUtils.APP_ID, PayUtils.APP_PRIVATE_KEY, PayUtils.FORMAT, PayUtils.CHARSET, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.SIGN_TYPE);
            AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
            AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
            model.setOutBizNo(order);
            model.setPayeeType("ALIPAY_USERID");
            model.setPayeeAccount(payeeAccount);
            model.setAmount(amount);
            model.setPayerShowName("红包提现转账");
            request.setBizModel(model);
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return 0;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public TransQueryDto queryTrans(String outBizNo) {
        try {
            alipayClient = new DefaultAlipayClient(PayUtils.URL, PayUtils.APP_ID, PayUtils.APP_PRIVATE_KEY, PayUtils.FORMAT, PayUtils.CHARSET, PayUtils.ALIPAY_PUBLIC_KEY, PayUtils.SIGN_TYPE);
            AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
            AlipayFundTransOrderQueryModel model = new AlipayFundTransOrderQueryModel();
            model.setOutBizNo(outBizNo);
            request.setBizModel(model);
            AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
            TransQueryDto dto = new TransQueryDto();
            dto.setOrderId(response.getOrderId());
            dto.setStatus(response.getStatus());
            dto.setPayDate(response.getPayDate());
            dto.setFailReason(response.getFailReason());
            dto.setOutBizNo(response.getOutBizNo());
            dto.setErrorCode(response.getErrorCode());
            return dto;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
