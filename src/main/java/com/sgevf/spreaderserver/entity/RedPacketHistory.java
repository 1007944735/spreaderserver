package com.sgevf.spreaderserver.entity;

public class RedPacketHistory {
    private Integer id;
    private String robMoney;
    private String robTime;
    private Integer redPacketId;
    private Integer robberId;

    public Integer getRobberId() {
        return robberId;
    }

    public void setRobberId(Integer robberId) {
        this.robberId = robberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Integer redPacketId) {
        this.redPacketId = redPacketId;
    }

    public String getRobMoney() {
        return robMoney;
    }

    public void setRobMoney(String robMoney) {
        this.robMoney = robMoney;
    }

    public String getRobTime() {
        return robTime;
    }

    public void setRobTime(String robTime) {
        this.robTime = robTime;
    }
}
