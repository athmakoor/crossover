package com.crossover.imagesearch.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.ESResponseHit;
import com.crossover.imagesearch.bean.UploadedImage;
import com.crossover.imagesearch.bean.query.ESQuery;
import com.crossover.imagesearch.bean.query.Query;
import com.crossover.imagesearch.properties.PropertyManager;
import com.crossover.imagesearch.service.UploadedImageService;
import com.crossover.imagesearch.service.mapper.ServiceMapper;
import com.crossover.imagesearch.util.elasticsearch.ElasticSearchService;

@Service
public class UploadedImageServiceImpl implements UploadedImageService {
    private ServiceMapper<UploadedImage, ESResponse> serviceMapper;
    private ElasticSearchService elasticSearchService;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    public UploadedImageServiceImpl() {
        super();
    }

    @Autowired
    public UploadedImageServiceImpl(ServiceMapper<UploadedImage, ESResponse> serviceMapper, ElasticSearchService elasticSearchService) {
        this.serviceMapper = serviceMapper;
        this.elasticSearchService = elasticSearchService;
    }

    @Override
    public List<UploadedImage> search(String description, String fileType, Integer minFileSize, Integer maxFileSize, Integer page, Integer pageSize) {
        List<UploadedImage> results = new ArrayList<>();
        Integer pageNumber = page == null ? 0 : page;
        Integer pageS = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;

        ESQuery query = constructESObject(description, fileType, minFileSize, maxFileSize, pageNumber, pageS);
        String path = PropertyManager.getElasticSearchPath() + "/" + PropertyManager.getElasticSearchIndex() + "/_search";

        ESResponse esResponse = elasticSearchService.search(query, path);

        for (ESResponseHit hit : esResponse.getHits().getHits()) {
            results.add(new UploadedImage(hit.get_source()));
        }

        return results;
    }

    private ESQuery constructESObject(String description, String fileType, Integer minFileSize, Integer maxFileSize, Integer page, Integer pageSize) {
        ESQuery esQuery = new ESQuery();

        esQuery.setFrom(page * pageSize);
        esQuery.setSize(pageSize);

        Query query = new Query();

        if (description != null && !description.isEmpty()) {
            Map<String, Object> descQuery = new HashMap<>();
            Map<String, Map<String, Object>> descMatch = new HashMap<>();
            descQuery.put("description", description);
            descMatch.put("term", descQuery);

            query.getBool().getMust().add(descMatch);
        }

        if (fileType != null && !fileType.isEmpty()) {
            Map<String, Object> fileTypeQuery = new HashMap<>();
            Map<String, Map<String, Object>> fileTypeFilter = new HashMap<>();
            fileTypeQuery.put("file_type", fileType);
            fileTypeFilter.put("term", fileTypeQuery);

            query.getBool().getMust().add(fileTypeFilter);
        }

        if (minFileSize != null || maxFileSize != null) {
            Map<String, Integer> fileSizeQuery = new HashMap<>();

            if(minFileSize != null) {
                fileSizeQuery.put("gte", minFileSize);
            }

            if(maxFileSize != null) {
                fileSizeQuery.put("lte", maxFileSize);
            }

            Map<String, Object> fileSizeMatch = new HashMap<>();
            fileSizeMatch.put("file_size", fileSizeQuery);
            Map<String, Map<String, Object>> rangeQuery = new HashMap<>();

            rangeQuery.put("range", fileSizeMatch);

            query.getBool().getMust().add(rangeQuery);
        }

        esQuery.setQuery(query);

        return esQuery;
    }
}
