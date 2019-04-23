package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.UserCard;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.UserCardService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class S0030 {
    @Autowired
    private UserCardService userCardService;

    @ResponseBody
    @RequestMapping(value = "/S0030", method = RequestMethod.POST)
    public Response<UserCard> s0030(@RequestParam("userCardId") int userCardId) {
        UserCard userCard = userCardService.queryUserCardById(userCardId);
        return new Response<>(HttpResponse.SUCCESS, "成功", userCard);
    }
}
