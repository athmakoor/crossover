package com.crossover.imagesearch.service;

import java.io.IOException;
import java.util.List;

import com.crossover.imagesearch.bean.SearchDTO;
import com.crossover.imagesearch.bean.UploadedImage;

public interface UploadedImageService {
    List<UploadedImage> search(String description, String fileType, Integer minFileSize, Integer maxFileSize, Integer page, Integer pageNumber);
}
