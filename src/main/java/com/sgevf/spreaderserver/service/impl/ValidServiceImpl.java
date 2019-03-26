package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.ValidService;
import com.sgevf.spreaderserver.utils.DateUtils;
import com.sgevf.spreaderserver.utils.UUIDUtils;
import com.sgevf.spreaderserver.utils.ValidImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ValidServiceImpl implements ValidService {
    @Autowired
    private RedisService redisService;
    private ValidImageUtils valid;
    private String code;
    private String validUrl;
    private String uuuid;
    @Value("${url}")
    private String url;
    @Value("${valid-url-path}")
    private String validUrlPath;


    @Override
    public Map<String, String> getValidUrl() {
        Map<String, String> result = new HashMap<>();
        valid = new ValidImageUtils(320, 80);
        code = valid.getCode();
        uuuid = UUIDUtils.getUUID();
        String fileName = DateUtils.formatCurTime() + ".png";
        String path = validUrlPath + fileName;
        validUrl = url + fileName;
        try {
            valid.write(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        redisService.set(uuuid, code, 2, 15, TimeUnit.MINUTES);
        result.put("uuuid", uuuid);
        result.put("validUrl", validUrl);
        return result;
    }
}
