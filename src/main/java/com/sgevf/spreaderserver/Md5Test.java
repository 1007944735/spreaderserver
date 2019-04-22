package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.DateUtils;
import sun.awt.geom.AreaOp;

import java.util.Calendar;
import java.util.Date;

public class Md5Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date("2019-04-22 13:41:35"));
        Date et = DateUtils.string2Date("0000-00-01 00:00:00");
        if (et.getDay() != 0) {
            calendar.add(Calendar.DATE, et.getDay());
        }
        if (et.getHours() != 0) {
            calendar.add(Calendar.HOUR, et.getHours());
        }
        if (et.getMinutes() != 0) {
            calendar.add(Calendar.MINUTE, et.getMinutes());
        }
        Calendar etCalendar = Calendar.getInstance();
        etCalendar.setTime(DateUtils.string2Date("2019-11-08 10:58:00"));
        System.out.println(calendar.compareTo(etCalendar)==-1 ? DateUtils.date2String(calendar.getTime()) : "2019-11-08 10:58:00");
    }
}
