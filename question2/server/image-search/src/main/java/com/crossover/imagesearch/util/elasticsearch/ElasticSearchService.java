package com.crossover.imagesearch.util.elasticsearch;

import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.query.ESQuery;

public interface ElasticSearchService {
    ESResponse search(ESQuery query, String path);
}
