package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.dto.ManageBusinessDto;
import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.BusinessService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class V0001 {
    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/V0001", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, List<ManageBusinessDto>>> v0001() {
        Map<String, List<ManageBusinessDto>> map = new HashMap<>();
        List<ManageBusinessDto> result = businessService.queryBusiness();
        map.put("list", result);
        return new Response<>(HttpResponse.SUCCESS, "成功", map);
    }
}
