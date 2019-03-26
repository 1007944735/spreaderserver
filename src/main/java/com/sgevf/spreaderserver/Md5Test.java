package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.RSAUtils;

import java.util.Map;

public class Md5Test {
    public static void main(String[] args) {
//        try {
//            byte[] publicKey = RSAUtils.base64Decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKULY5eU5Zmg3FhA+bOykvTWxBfhiiyJLdX5ylK+LF1Cu2nbJb1G0ThLXxuPCZhdy/PDl8XGzXcoj8svqGWS3sCAwEAAQ==");
//            String data = "123456";
//            String s=RSAUtils.base64Encode(RSAUtils.encryptByPublicKey(data.getBytes("utf-8"), publicKey));
//            System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        String a="123456";
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
        try {
            Map<String,Object> keys=RSAUtils.initKey();
            String data="123456";
            System.out.println("加密内容==============》"+data);
            byte[] privateKey=RSAUtils.getPrivateKey(keys);
            byte[] publicKey=RSAUtils.getPublicKey(keys);

            byte[] enData=RSAUtils.encryptByPublicKey(data.getBytes("utf-8"),publicKey);
            String base64Data=RSAUtils.base64Encode(enData);
            System.out.println("RSA,BASE64加密内容==============》"+base64Data);

            byte[] deData=RSAUtils.base64Decode(base64Data);
            byte[] d=RSAUtils.decryptByPrivateKey(deData,privateKey);
            String od=new String(d,"utf-8");
            System.out.println("解密内容==============》"+od);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
