package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class S0035 {
    @Autowired
    private RedisService redisService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/S0035", method = RequestMethod.POST)
    @ResponseBody
    public Response<Map<String, String>> s0035(@RequestParam("token") String token,
                                               @RequestParam("bName") String bName,
                                               @RequestParam("bLicense") String bLicense,
                                               @RequestParam("bLogo") String bLogo,
                                               @RequestParam("bIdcardFront") String bIdcardFront,
                                               @RequestParam("bIdcardBack") String bIdcardBack,
                                               @RequestParam("bAddress") String bAddress,
                                               @RequestParam("bSocialCredit") String bSocialCredit,
                                               @RequestParam("bPhone") String bPhone,
                                               @RequestParam("bContent") String bContent
    ) {
        try {
            String userId = redisService.get(token, 1);

            Business b = businessService.queryBusinessByUserId(Integer.valueOf(userId));
            if (b != null) {
                //商家已存在,更新
                b.setbName(bName);
                b.setUserId(Integer.valueOf(userId));
                b.setbLicense(bLicense);
                b.setbLogo(bLogo);
                b.setbIdcardFront(bIdcardFront);
                b.setbIdcardBack(bIdcardBack);
                b.setbAddress(bAddress);
                b.setbSocialCredit(bSocialCredit);
                b.setbPhone(bPhone);
                b.setbContent(bContent);
                businessService.updateBusiness(b);
                userService.updateIsBusiness(Integer.valueOf(userId), "1");//无后台管理界面，直接审核通过
                Map<String, String> map = new HashMap<>();
                map.put("id", b.getId() + "");
                return new Response<>(HttpResponse.SUCCESS, "成功", map);
            } else {
                Business business = new Business();
                business.setbName(bName);
                business.setUserId(Integer.valueOf(userId));
                business.setbLicense(bLicense);
                business.setbLogo(bLogo);
                business.setbIdcardFront(bIdcardFront);
                business.setbIdcardBack(bIdcardBack);
                business.setbAddress(bAddress);
                business.setbSocialCredit(bSocialCredit);
                business.setbPhone(bPhone);
                business.setbContent(bContent);
                businessService.insertBusiness(business);
                userService.updateIsBusiness(Integer.valueOf(userId), "1");//无后台管理界面，直接审核通过
                Map<String, String> map = new HashMap<>();
                map.put("id", business.getId() + "");
                return new Response<>(HttpResponse.SUCCESS, "成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
