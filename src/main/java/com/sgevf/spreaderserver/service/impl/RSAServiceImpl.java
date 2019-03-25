package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.service.RSAService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
            redisService.set(uuid, RSAUtils.byte2String(privateKey),0);
            return RSAUtils.byte2String(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
