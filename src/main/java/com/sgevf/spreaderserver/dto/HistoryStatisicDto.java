package com.sgevf.spreaderserver.dto;

import java.util.List;

public class HistoryStatisicDto {
    public String money;
    public String num;
    public String reNum;
    public String reMoney;

    public String getReMoney() {
        return reMoney;
    }

    public void setReMoney(String reMoney) {
        this.reMoney = reMoney;
    }

    public List<HistoryStatisicListDto> list;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getReNum() {
        return reNum;
    }

    public void setReNum(String reNum) {
        this.reNum = reNum;
    }

    public List<HistoryStatisicListDto> getList() {
        return list;
    }

    public void setList(List<HistoryStatisicListDto> list) {
        this.list = list;
    }
}
