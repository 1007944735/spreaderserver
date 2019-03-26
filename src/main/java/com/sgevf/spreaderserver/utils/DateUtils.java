package com.sgevf.spreaderserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DEFAULT="yyyyMMddHHmmss";
    public static final String NORMAL="yyyy-MM-dd HH:mm:ss";
    /**
     * 获取当前的时间 yyyymmddHHmmss
     * @return
     */
    public static String formatCurTime(){
        SimpleDateFormat format=new SimpleDateFormat(DEFAULT);
        return format.format(new Date());
    }

    public static String formatCurTimeByNormal(){
        SimpleDateFormat format=new SimpleDateFormat(NORMAL);
        return format.format(new Date());
    }

    public static String format(String ft){
        SimpleDateFormat format=new SimpleDateFormat(ft);
        return format.format(new Date());
    }
}
