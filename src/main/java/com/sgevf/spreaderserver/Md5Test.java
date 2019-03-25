package com.sgevf.spreaderserver;

import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Md5Test {
    public static void main(String[] args) {
        try {
            byte[] publicKey = RSAUtils.string2Byte("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJHxKCYTXb+UUDjz3TBITOkmfeojbzsA2qzdB7l5F0fU4JIA5khFHJxkeq6ygi8kTJA8qjnmJoiq22OppWfXujECAwEAAQ==");
            String data = "123456";
            String s=RSAUtils.byte2String(RSAUtils.encryptByPublicKey(RSAUtils.string2Byte(data), publicKey));
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
