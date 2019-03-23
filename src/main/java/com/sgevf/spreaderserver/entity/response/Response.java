package com.sgevf.spreaderserver.entity.response;

public class Response<T>{
    public static String SUCCESS="200";
    public static String ERROR="-1";
    private String reCode;
    private String reInfo;
    private T params;

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getReInfo() {
        return reInfo;
    }

    public void setReInfo(String reInfo) {
        this.reInfo = reInfo;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
