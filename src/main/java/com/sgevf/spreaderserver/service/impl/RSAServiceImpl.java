package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.service.RSAService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RSAServiceImpl implements RSAService {
    @Autowired
    private RedisService redisService;

    @Override
    public String createRSA(String uuid) {
        try {
            Map<String, Object> keys = RSAUtils.initKey();
            byte[] publicKey = RSAUtils.getPublicKey(keys);
            byte[] privateKey = RSAUtils.getPrivateKey(keys);
            redisService.set(uuid, RSAUtils.base64Encode(privateKey),0,5,TimeUnit.HOURS);
            return RSAUtils.base64Encode(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
