package com.sgevf.spreaderserver.service;

public interface PayService {
    String pubPay(String amount, String order);

    void grantFromAli();
}
