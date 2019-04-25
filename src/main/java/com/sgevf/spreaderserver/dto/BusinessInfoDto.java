package com.sgevf.spreaderserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessInfoDto {
    private Integer id;
    private String bName;
    private String bTime;
    private String bLicense;
    private String bLogo;
    private String bIdcardFront;
    private String bIdcardBack;
    private String bAddress;
    private String bSocialCredit;
    private String bPhone;
    private String bContent;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbTime() {
        return bTime;
    }

    public void setbTime(String bTime) {
        this.bTime = bTime;
    }

    public String getbLicense() {
        return bLicense;
    }

    public void setbLicense(String bLicense) {
        this.bLicense = bLicense;
    }

    public String getbLogo() {
        return bLogo;
    }

    public void setbLogo(String bLogo) {
        this.bLogo = bLogo;
    }

    public String getbIdcardFront() {
        return bIdcardFront;
    }

    public void setbIdcardFront(String bIdcardFront) {
        this.bIdcardFront = bIdcardFront;
    }

    public String getbIdcardBack() {
        return bIdcardBack;
    }

    public void setbIdcardBack(String bIdcardBack) {
        this.bIdcardBack = bIdcardBack;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getbSocialCredit() {
        return bSocialCredit;
    }

    public void setbSocialCredit(String bSocialCredit) {
        this.bSocialCredit = bSocialCredit;
    }

    public String getbPhone() {
        return bPhone;
    }

    public void setbPhone(String bPhone) {
        this.bPhone = bPhone;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
