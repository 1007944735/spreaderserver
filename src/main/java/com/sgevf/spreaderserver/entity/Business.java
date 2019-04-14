package com.sgevf.spreaderserver.entity;

public class Business {
    private Integer id;
    private String bName;
    private String bTime;
    private String bLicense;
    private String bLogo;
    private String bIdcardFront;
    private String bIdcardBack;
    private String b_address;
    private String bSocialCredit;
    private String bContext;

    private Integer userId;

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

    public String getB_address() {
        return b_address;
    }

    public void setB_address(String b_address) {
        this.b_address = b_address;
    }

    public String getbSocialCredit() {
        return bSocialCredit;
    }

    public void setbSocialCredit(String bSocialCredit) {
        this.bSocialCredit = bSocialCredit;
    }

    public String getbContext() {
        return bContext;
    }

    public void setbContext(String bContext) {
        this.bContext = bContext;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
