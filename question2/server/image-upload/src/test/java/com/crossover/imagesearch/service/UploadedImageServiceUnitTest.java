package com.crossover.imagesearch.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.imagesearch.ImageUploadApplication;
import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.UploadedImage;
import com.crossover.imagesearch.service.impl.UploadedImageServiceImpl;
import com.crossover.imagesearch.service.mapper.ServiceMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageUploadApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadedImageServiceUnitTest {

    @Resource
    private ServiceMapper<UploadedImage, ESResponse> serviceMapper;

    private UploadedImageServiceImpl uploadedImageService;

    @Before
    public void setUp() {
        uploadedImageService = new UploadedImageServiceImpl(serviceMapper);
    }

    @Test
    public void createSuccess() throws IOException {
        /*UploadedImage uploadedImage = new UploadedImage("Description", "image/jpg", "12000");

        ClassPathResource resource = new ClassPathResource("download.jpeg");
        File file = resource.getFile();
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                resource.getFile().getName(), "image/jpg", IOUtils.toByteArray(input));
        UploadedImage savedImage = uploadedImageService.create(uploadedImage, multipartFile);
        assertNull(savedImage);*/

    }
}
