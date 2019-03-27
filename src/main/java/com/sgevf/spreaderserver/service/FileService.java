package com.sgevf.spreaderserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadImage(MultipartFile file);

    String uploadVideo(MultipartFile file);
}
