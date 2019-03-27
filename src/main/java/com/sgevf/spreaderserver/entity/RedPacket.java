package com.sgevf.spreaderserver.entity;

public class RedPacket {
    private Integer id;
    private String amount;
    private String type;
    private String pubTime;
    private String pubLongitude;
    private String pubLatitude;
    private String startTime;
    private String endTime;
    private String maxNumber;
    private String pubAddress;

    private Integer puberId;
    private Integer expandId;

    public Integer getPuberId() {
        return puberId;
    }

    public void setPuberId(Integer puberId) {
        this.puberId = puberId;
    }

    public Integer getExpandId() {
        return expandId;
    }

    public void setExpandId(Integer expandId) {
        this.expandId = expandId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getPubLongitude() {
        return pubLongitude;
    }

    public void setPubLongitude(String pubLongitude) {
        this.pubLongitude = pubLongitude;
    }

    public String getPubLatitude() {
        return pubLatitude;
    }

    public void setPubLatitude(String pubLatitude) {
        this.pubLatitude = pubLatitude;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getPubAddress() {
        return pubAddress;
    }

    public void setPubAddress(String pubAddress) {
        this.pubAddress = pubAddress;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
