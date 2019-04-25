package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class S0034 {
    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/S0034", method = RequestMethod.POST)
    public Response<Map<String, String>> s0034(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        String filePath = fileService.uploadFile(file);
        if (filePath != null && !filePath.isEmpty()) {
            result.put("filePath", filePath);
            return new Response<>(HttpResponse.SUCCESS, "成功", result);
        } else {
            return new Response<>(HttpResponse.ERROR, "上传失败", null);
        }
    }
}
