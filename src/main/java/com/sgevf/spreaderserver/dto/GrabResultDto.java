package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrabResultDto {
    private String money;
    private String name;
    private List<CardListDto> list;

    public String getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<CardListDto> getList() {
        return list;
    }

    public void setList(List<CardListDto> list) {
        this.list = list;
    }
}
