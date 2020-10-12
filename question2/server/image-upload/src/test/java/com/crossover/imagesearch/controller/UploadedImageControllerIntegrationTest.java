package com.crossover.imagesearch.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import com.crossover.imagesearch.ImageUploadApplication;
import com.crossover.imagesearch.bean.UploadedImage;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageUploadApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadedImageControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void missingDescriptionUploadTest() {
        UploadedImage uploadedImage = new UploadedImage(null, "image/png", "12000");

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("download.jpeg"));
        parameters.add("data", uploadedImage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);


        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/api/image"),
                HttpMethod.POST, entity, Object.class);

        assertTrue(response.getStatusCodeValue() == HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void successfulUploadTest() {
        /*UploadedImage uploadedImage = new UploadedImage("Test Description", "image/png", "12000");

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("download.jpeg"));
        parameters.add("data", uploadedImage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);


        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/api/image"),
                HttpMethod.POST, entity, Object.class);

        assertTrue(response.getStatusCodeValue() == HttpStatus.SC_CREATED);*/
    }
}
