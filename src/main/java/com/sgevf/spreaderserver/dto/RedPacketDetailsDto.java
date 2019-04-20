package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedPacketDetailsDto {
    private Integer id;
    private Integer sponserId;
    private String sponserName;
    private String sponserImage;

    private Double amount;
    private String type;
    private String pubTime;
    private String pubLongitude;
    private String pubLatitude;
    private String startTime;
    private String endTime;
    private Integer maxNumber;
    private String pubAddress;
    private Double distance;
    private String title;
    private String info;
    private String isGrab;

    private List<CardListDto> list;

    public String getIsGrab() {
        return isGrab;
    }

    public void setIsGrab(String isGrab) {
        this.isGrab = isGrab;
    }

    public RedPacketDetailsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSponserId() {
        return sponserId;
    }

    public void setSponserId(Integer sponserId) {
        this.sponserId = sponserId;
    }

    public String getSponserName() {
        return sponserName;
    }

    public void setSponserName(String sponserName) {
        this.sponserName = sponserName;
    }

    public String getSponserImage() {
        return sponserImage;
    }

    public void setSponserImage(String sponserImage) {
        this.sponserImage = sponserImage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getPubAddress() {
        return pubAddress;
    }

    public void setPubAddress(String pubAddress) {
        this.pubAddress = pubAddress;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<CardListDto> getList() {
        return list;
    }

    public void setList(List<CardListDto> list) {
        this.list = list;
    }
}
