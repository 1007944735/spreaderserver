package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.dto.AlipayUserInfoDto;
import com.sgevf.spreaderserver.dto.TransQueryDto;

public interface AliService {
    String pubPay(String amount, String order);

    String getAlipayAuthInfo(String userId);

    String getAlipayAccessToken(String authCode);

    AlipayUserInfoDto getAliUserInfo(String accessToken);

    int transferAccounts(String order,String payeeAccount,String amount);

    TransQueryDto queryTrans(String outBizNo);
}
