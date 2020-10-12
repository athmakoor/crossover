package com.crossover.imageupload.controller;

import java.io.IOException;
import java.net.URI;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crossover.imageupload.bean.UploadedImage;
import com.crossover.imageupload.service.UploadedImageService;
import com.crossover.imageupload.validator.UploadedImageValidator;

@RestController
@RequestMapping("/api")
@Validated
public class UploadedImageController {
    @Resource
    private UploadedImageService uploadedImageService;

    private UploadedImageValidator uploadedImageValidator = new UploadedImageValidator();

    @PostMapping(value = "/image", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> createProduct(@Valid @RequestPart(value = "data") UploadedImage uploadImageRequest,
                                                @RequestPart(value = "file") MultipartFile file, BindingResult bindingResult) throws IOException, BindException {
        uploadedImageValidator.validate(uploadImageRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        UploadedImage savedImage = uploadedImageService.create(uploadImageRequest, file);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedImage.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
