package com.crossover.imageupload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.crossover.imageupload.ImageUploadApplication;
import com.crossover.imageupload.bean.UploadedImage;
import com.crossover.imageupload.bean.jpa.UploadedImageEntity;
import com.crossover.imageupload.repository.UploadedImageRepository;
import com.crossover.imageupload.service.impl.UploadedImageServiceImpl;
import com.crossover.imageupload.service.mapper.ServiceMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageUploadApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadedImageServiceUnitTest {
    @Mock
    private UploadedImageRepository uploadedImageRepository;

    @Resource
    private ServiceMapper<UploadedImage, UploadedImageEntity> serviceMapper;

    private UploadedImageServiceImpl uploadedImageService;

    @Before
    public void setUp() {
        uploadedImageService = new UploadedImageServiceImpl(uploadedImageRepository, serviceMapper);
        when(uploadedImageRepository.save(Mockito.any(UploadedImageEntity.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void createSuccess() throws IOException {
        UploadedImage uploadedImage = new UploadedImage("Description", "image/jpg", 12000);

        ClassPathResource resource = new ClassPathResource("download.jpeg");
        File file = resource.getFile();
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                resource.getFile().getName(), "image/jpg", IOUtils.toByteArray(input));
        UploadedImage savedImage = uploadedImageService.create(uploadedImage, multipartFile);
        assertNotNull(savedImage);

    }
}
