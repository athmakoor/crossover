package com.crossover.imagesearch.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import com.crossover.imagesearch.ImageSearchApplication;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageSearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadedImageControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void searchWithParamsTest() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);


        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/api/image/search?description=df&imageType=png&minSize=100&maxSize=1000000"),
                HttpMethod.GET, entity, Object.class);

        assertTrue(response.getStatusCodeValue() == HttpStatus.SC_OK);
    }
}
