package com.crossover.imagesearch.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.imagesearch.ImageSearchApplication;
import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.UploadedImage;
import com.crossover.imagesearch.config.TestConfig;
import com.crossover.imagesearch.service.impl.UploadedImageServiceImpl;
import com.crossover.imagesearch.service.mapper.ServiceMapper;
import com.crossover.imagesearch.util.elasticsearch.ElasticSearchService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageSearchApplication.class)
@Import(TestConfig.class)
public class UploadedImageServiceUnitTest {

    @Autowired
    private ServiceMapper<UploadedImage, ESResponse> serviceMapper;

    @Autowired
    private ElasticSearchService elasticSearchService;

    private UploadedImageServiceImpl uploadedImageService;

    @Before
    public void setUp() {
        uploadedImageService = new UploadedImageServiceImpl(serviceMapper, elasticSearchService);
    }

    @Test
    public void searchSuccess() {
        List<UploadedImage> result = uploadedImageService.search("description", "png", 0, null, null, null);

        assertTrue(!result.isEmpty());
    }
}
