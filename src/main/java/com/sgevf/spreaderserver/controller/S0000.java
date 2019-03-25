package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.RSADto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RSAService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0000 {
    @Autowired
    private RSAService rsaService;

    @ResponseBody
    @RequestMapping(value = "/S0000", method = RequestMethod.POST)
    public Response<RSADto> s0000(@RequestHeader("uuid") String uuid) {
        String publicKey = rsaService.createRSA(uuid);
        if (publicKey != null) {
            return new Response<>(HttpResponse.SUCCESS, "成功", new RSADto(publicKey));
        } else {
            return new Response<>(HttpResponse.ERROR, "获取RSA公钥失败", null);
        }
    }
}
