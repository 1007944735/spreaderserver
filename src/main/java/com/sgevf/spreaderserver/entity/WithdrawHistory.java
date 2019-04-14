package com.sgevf.spreaderserver.entity;

public class WithdrawHistory {
    private Integer id;
    private String money;
    private String time;
    private String way;
    private String status;
    private String failReason;
    private String withdrawOrder;

    private Integer takerId;

    public Integer getTakerId() {
        return takerId;
    }

    public void setTakerId(Integer takerId) {
        this.takerId = takerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getWithdrawOrder() {
        return withdrawOrder;
    }

    public void setWithdrawOrder(String withdrawOrder) {
        this.withdrawOrder = withdrawOrder;
    }
}
