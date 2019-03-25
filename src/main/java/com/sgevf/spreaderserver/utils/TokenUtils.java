package com.sgevf.spreaderserver.utils;

public class TokenUtils {
    public static String createToken(Integer id,String uuid){
        String s=id+uuid;
        byte[] bytes=s.getBytes();
        return RSAUtils.base64Encode(bytes);
    }
}
