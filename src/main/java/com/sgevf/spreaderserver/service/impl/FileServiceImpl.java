package com.sgevf.spreaderserver.service.impl;

import com.sgevf.spreaderserver.service.FileService;
import com.sgevf.spreaderserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 文件上传
 */
@Service
public class FileServiceImpl implements FileService {
    private static String PICTURE = "picture";
    private static String VIDEO = "video";

    //图片存放路径
    @Value("${picture-url-path}")
    private String pictureUrlPath;
    //视频存放路径
    @Value("${video-url-path}")
    private String videoUrlPath;
    //文件存放路径
    @Value("${file-url-path}")
    private String fileUrlPath;

    @Value("${url}")
    private String url;

    @Override
    public String uploadImage(MultipartFile file) {
        if (file == null) {
            return "";
        }
        String[] filePart = file.getOriginalFilename().split("\\.");
        String dateTime = DateUtils.formatCurTime();
        String fileName = PICTURE + getRandomString(4) + dateTime + "." + filePart[filePart.length - 1];
        File f = new File(pictureUrlPath + fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return url + "picture/" + fileName;
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        if (file == null) {
            return "";
        }
        String[] filePart = file.getOriginalFilename().split("\\.");
        String dateTime = DateUtils.formatCurTime();
        String fileName = VIDEO + getRandomString(4) + dateTime + "." + filePart[filePart.length - 1];
        File f = new File(videoUrlPath + fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return url + "video/" + fileName;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null) {
            return "";
        }
        String[] filePart = file.getOriginalFilename().split("\\.");
        String dateTime = DateUtils.formatCurTime();
        String fileName = "file" + getRandomString(4) + dateTime + "." + filePart[filePart.length - 1];
        File f = new File(fileUrlPath+fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return url+"file/"+fileName;
    }

    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
