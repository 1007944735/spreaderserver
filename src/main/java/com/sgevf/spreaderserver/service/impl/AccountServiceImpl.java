package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.dao.AccountMapper;
import com.sgevf.spreaderserver.entity.Account;
import com.sgevf.spreaderserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account selectAccountById(int id) {
        return accountMapper.selectAccountById(id);
    }

    @Override
    public int insertAccount(int userId) {
        return accountMapper.insertAccount(userId);
    }

    @Override
    public int increaseBalance(double money, int userId) {
        return accountMapper.increaseBalance(money, userId);
    }

    @Override
    public int reduceBalance(double money, int userId) {
        return accountMapper.reduceBalance(money,userId);
    }

    @Override
    public Account selectAccountByUserId(int userId) {
        return accountMapper.selectAccountByUserId(userId);
    }

    @Override
    public int bindAlipayAccount(String alipayAccount, String alipayHead, String alipayName, int userId) {
        return accountMapper.updateAlipayAccount(alipayAccount, alipayHead, alipayName, userId);
    }
}
