package com.crossover.imageupload.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crossover.imageupload.bean.UploadedImage;
import com.crossover.imageupload.bean.jpa.UploadedImageEntity;
import com.crossover.imageupload.repository.UploadedImageRepository;
import com.crossover.imageupload.service.UploadedImageService;
import com.crossover.imageupload.service.mapper.ServiceMapper;
import com.crossover.imageupload.util.S3Uploader;

@Service
public class UploadedImageServiceImpl implements UploadedImageService {
    private UploadedImageRepository uploadedImageRepository;
    private ServiceMapper<UploadedImage, UploadedImageEntity> serviceMapper;

    private static final String s3Bucket = "crossover-image";

    public UploadedImageServiceImpl() {
        super();
    }

    @Autowired
    public UploadedImageServiceImpl(UploadedImageRepository uploadedImageRepository, ServiceMapper<UploadedImage, UploadedImageEntity> serviceMapper) {
        this.uploadedImageRepository = uploadedImageRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public UploadedImage create(UploadedImage uploadImageRequest, MultipartFile file) throws IOException {
        UploadedImageEntity entity = new UploadedImageEntity();

        byte[] byteArr;
        String base64Image;
        String imageName = "" + new Date().getTime() + "-" + file.getOriginalFilename();

        serviceMapper.mapDTOToEntity(uploadImageRequest, entity);

        byteArr = file.getBytes();
        base64Image = Base64.getEncoder().encodeToString(byteArr);

        String savedUrl = S3Uploader.uploadImage(s3Bucket, base64Image, "images/" + imageName);

        entity.setImageUrl(savedUrl);

        UploadedImageEntity savedEntity = uploadedImageRepository.save(entity);

        return serviceMapper.mapEntityToDTO(savedEntity, UploadedImage.class);
    }
}
