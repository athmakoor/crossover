package com.crossover.imageupload.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.imageupload.bean.jpa.UploadedImageEntity;

public interface UploadedImageRepository extends PagingAndSortingRepository<UploadedImageEntity, Integer> {
}
