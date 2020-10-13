package com.crossover.imagesearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.imagesearch.ImageSearchApplication;
import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.query.ESQuery;
import com.crossover.imagesearch.properties.PropertyManager;
import com.crossover.imagesearch.util.elasticsearch.ElasticSearchService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageSearchApplication.class)
public class ElasticSearchUnitTest {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    public void searchSuccess() {
        ESQuery query = new ESQuery();
        String path = PropertyManager.getElasticSearchPath() + "/" + PropertyManager.getElasticSearchIndex() + "/_search";
        ESResponse result = elasticSearchService.search(query, path);

        assertTrue(!result.getHits().getHits().isEmpty());
    }
}
