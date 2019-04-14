package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Account;

public interface AccountService {
    Account selectAccountById(int id);

    int insertAccount(int userId);

    int increaseBalance(double money, int userId);

    int reduceBalance(double money, int userId);

    Account selectAccountByUserId(int userId);

    int bindAlipayAccount(String alipayAccount, String alipayHead, String alipayName, int userId);
}
