package com.crossover.imagesearch.util.elasticsearch;

import org.springframework.stereotype.Service;

import com.crossover.imagesearch.bean.ESResponse;
import com.crossover.imagesearch.bean.query.ESQuery;
import com.crossover.imagesearch.util.request.Request;
import com.crossover.imagesearch.util.request.RequestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    public ESResponse search(ESQuery query, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(query);
            Request request = new Request(path, "POST",json);

            String response = RequestUtils.getResponse(request);

            Gson gson = new Gson();
            ESResponse esResponse = gson.fromJson(response, ESResponse.class);

            return esResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}