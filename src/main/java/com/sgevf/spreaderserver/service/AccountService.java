package com.sgevf.spreaderserver.service;

import com.sgevf.spreaderserver.entity.Account;

public interface AccountService {
    Account selectAccountById(int id);

    int insertAccount(int userId);

    int updateBalance(double money, int userId);

    Account selectAccountByUserId(int userId);
}
