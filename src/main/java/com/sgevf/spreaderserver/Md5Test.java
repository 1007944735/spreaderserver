package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Md5Test {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));
        String str = "13|13";
        String[] strings = str.split("\\|");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse("0000-00-02 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
