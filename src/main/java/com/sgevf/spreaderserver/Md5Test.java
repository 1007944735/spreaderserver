package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.RSAUtils;

public class Md5Test {
    public static void main(String[] args) {
        try {
            byte[] publicKey = RSAUtils.base64Decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKF7YxFboLLBgM3RgwSxvYS3mY784Few1T27KXu/UltFyHSkmF17Ecl51ouyAHvV1d4BLTbj8fo1eWZUJgqEJoECAwEAAQ==");
            String data = "123456";
            String s=RSAUtils.base64Encode(RSAUtils.encryptByPublicKey(data.getBytes("utf-8"), publicKey));
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
        String a="123456";
//        try {
//            byte[] b=Base64.getEncoder().encode(a.getBytes("utf-8"));
//            String c=new String(Base64.getDecoder().decode(b));
//            System.out.println(c);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        byte[] b= null;
//        try {
//            b = RSAUtils.base64Decode(RSAUtils.base64Encode(a.getBytes("utf-8")));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new String(b));
    }
}
