package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Integer id;
    private double balance;
    private String alipayAccount;
    private String alipayHead;
    private String alipayName;
    private String isGrant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getAlipayHead() {
        return alipayHead;
    }

    public void setAlipayHead(String alipayHead) {
        this.alipayHead = alipayHead;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public String getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(String isGrant) {
        this.isGrant = isGrant;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName;

    }
}
