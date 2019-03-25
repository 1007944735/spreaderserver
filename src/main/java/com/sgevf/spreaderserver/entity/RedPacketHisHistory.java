package com.sgevf.spreaderserver.entity;

public class RedPacketHisHistory {
    private Integer id;
    private String robMoney;
    private String robTime;
    private RedPacket redPacketId;
    private User robberId;

    public RedPacket getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(RedPacket redPacketId) {
        this.redPacketId = redPacketId;
    }

    public User getRobberId() {
        return robberId;
    }

    public void setRobberId(User robberId) {
        this.robberId = robberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
