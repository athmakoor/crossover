package com.crossover.imagesearch.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.imagesearch.bean.UploadedImage;
import com.crossover.imagesearch.service.UploadedImageService;

@RestController
@RequestMapping("/api")
@Validated
public class UploadedImageController {
    @Resource
    private UploadedImageService uploadedImageService;

    @GetMapping(value = "/image/search")
    public ResponseEntity<Object> createProduct(@RequestParam(value = "description", required = false) final String description,
                                                @RequestParam(value = "imageType", required = false) final String imageType,
                                                @RequestParam(value = "minSize", required = false) final Integer minFileSize,
                                                @RequestParam(value = "maxSize", required = false) final Integer maxFileSize,
                                                @RequestParam(value = "page", required = false) final Integer page,
                                                @RequestParam(value = "pageSize", required = false) final Integer pageSize) {
        List<UploadedImage> images = uploadedImageService.search(description, imageType, minFileSize, maxFileSize, page, pageSize);

        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(images);
    }
}
