package com.crossover.imagesearch.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.crossover.imagesearch.bean.ESImageData;
import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.ESResponseHit;
import com.crossover.imagesearch.bean.query.ESQuery;
import com.crossover.imagesearch.util.elasticsearch.ElasticSearchService;
import com.crossover.imagesearch.util.elasticsearch.ElasticSearchServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    public ElasticSearchService elasticSearchService(){
        return new ElasticSearchServiceImpl() {
            @Override
            public ESResponse search(ESQuery query, String path) {
                ESResponse esResponse = new ESResponse();
                ESResponseHit hit = new ESResponseHit();
                ESImageData source = new ESImageData();
                hit.set_source(source);

                esResponse.getHits().getHits().add(hit);

                return esResponse;
            }
        };
    }
}
