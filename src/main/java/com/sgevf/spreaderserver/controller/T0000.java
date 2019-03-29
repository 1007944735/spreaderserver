package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.response.Response;
import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class T0000 {

    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/T0000", method = RequestMethod.POST)
    public Response<String> t0000(HttpServletRequest request, @RequestParam("url") String url) {
        List<MultipartFile> list=((MultipartHttpServletRequest) request).getFiles("file");
        for(MultipartFile file:list) {
            fileService.uploadImage(file);
        }
        System.out.println(url);
        return new Response<>(HttpResponse.SUCCESS, "200", null);
    }
}
