package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.RSAService;
import com.sgevf.spreaderserver.service.RedisService;
import com.sgevf.spreaderserver.service.UserService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import com.sgevf.spreaderserver.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0009 {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/S0009", method = RequestMethod.POST)
    public Response<String> s0009(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("token") String token, @RequestHeader("uuid") String uuid) {
        String userId = redisService.get(token, 1);
        User user = userService.queryUserById(Integer.valueOf(userId));
        String privateKey = redisService.get(uuid, 0);
        byte[] oldP = RSAUtils.base64Decode(oldPassword);
        byte[] newP = RSAUtils.base64Decode(newPassword);
        byte[] decodeOldPass = new byte[0];
        byte[] decodeNewPass = new byte[0];
        try {
            decodeOldPass = RSAUtils.decryptByPrivateKey(oldP, RSAUtils.base64Decode(privateKey));
            decodeNewPass = RSAUtils.decryptByPrivateKey(newP, RSAUtils.base64Decode(privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String op = DigestUtils.md5DigestAsHex(decodeOldPass);
        String np = DigestUtils.md5DigestAsHex(decodeNewPass);
        if (user.getPassword().equals(op)) {
            userService.updatePassword(user.getId(), np);
        } else {
            //原密码不正确
            return new Response<>(HttpResponse.ERROR, "原密码不正确", null);
        }
        return new Response<>(HttpResponse.SUCCESS, "成功", "修改成功");
    }
}
