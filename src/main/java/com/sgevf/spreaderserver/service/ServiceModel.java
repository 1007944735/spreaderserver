package com.sgevf.spreaderserver.service;

public class ServiceModel<T> {
    public int code;
    public T t;

    public ServiceModel(int code, T t) {
        this.code = code;
        this.t = t;
    }
}
