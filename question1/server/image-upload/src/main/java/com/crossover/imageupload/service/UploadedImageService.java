package com.crossover.imageupload.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.crossover.imageupload.bean.UploadedImage;

public interface UploadedImageService {
    UploadedImage create(UploadedImage uploadImageRequest, MultipartFile file) throws IOException;
}
