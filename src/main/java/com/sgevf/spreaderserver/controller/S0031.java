package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class S0031 {
    @ResponseBody
    @RequestMapping(value = "/S0031", method = RequestMethod.POST)
    public Response<Map<String, List<String>>> s0031() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("http://47.103.8.72:8080/spreader/slideshow/slideshow1.jpg");
        list.add("http://47.103.8.72:8080/spreader/slideshow/slideshow2.jpg");
        list.add("http://47.103.8.72:8080/spreader/slideshow/slideshow3.jpg");
        map.put("list", list);
        return new Response<>(HttpResponse.SUCCESS, "成功", map);
    }
}
