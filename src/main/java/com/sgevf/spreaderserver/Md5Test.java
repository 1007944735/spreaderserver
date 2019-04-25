package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.DateUtils;
import sun.awt.geom.AreaOp;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Md5Test {
    public static void main(String[] args) {
        double all = 100;
        int num = 10;
        double[] money=new double[10];
        double mm=0;
        for (int i = 0; i < num; i++) {
            if (i == num - 1) {
                System.out.println("第" + i + "个：" + all);
                money[i]=all;
            } else {
//                double maxNumber = all - 0.01 * (num - 1 - i);
                double maxNumber = all/(num-i)*2;
                double actual = (maxNumber - 0.01) * new Random().nextDouble() + 0.01;
                all = all - actual;
                money[i]=actual;
                System.out.println("第" + i + "个：" + actual);
            }
        }
        for(int i=0;i<money.length;i++){
            mm=mm+money[i];
        }
        System.out.println("总共：" + mm);
    }
}
