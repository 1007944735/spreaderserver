package com.sgevf.spreaderserver.entity.response;

public class Response<T>{
    private String reCode;
    private String reInfo;
    private T t;

    public Response(String reCode,String reInfo,T t){
        this.reCode=reCode;
        this.reInfo=reInfo;
        this.t=t;
    }

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
        return t;
    }

    public void setParams(T t) {
        this.t = t;
    }
}
