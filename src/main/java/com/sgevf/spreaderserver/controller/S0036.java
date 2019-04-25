package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.BusinessInfoDto;
import com.sgevf.spreaderserver.entity.Business;
import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0036 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/S0036", method = RequestMethod.POST)
    public Response<BusinessInfoDto> s0036(@RequestParam("token") String token) {
        try {
            String userId = redisService.get(token, 1);
            Business business = businessService.queryBusinessByUserId(Integer.valueOf(userId));
            BusinessInfoDto dto = new BusinessInfoDto();
            dto.setId(business.getId());
            dto.setbName(business.getbName());
            dto.setbTime(business.getbTime());
            dto.setbLicense(business.getbLicense());
            dto.setbLogo(business.getbLogo());
            dto.setbIdcardFront(business.getbIdcardFront());
            dto.setbIdcardBack(business.getbIdcardBack());
            dto.setbAddress(business.getbAddress());
            dto.setbSocialCredit(business.getbSocialCredit());
            dto.setbPhone(business.getbPhone());
            dto.setbContent(business.getbContent());
            User user = userService.queryUserById(Integer.valueOf(userId));
            dto.setStatus(user.getIsBusiness());
            return new Response<>(HttpResponse.SUCCESS, "成功", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(HttpResponse.ERROR, "系统错误", null);
        }
    }
}
