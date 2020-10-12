package com.crossover.imagesearch.bean;

public class ESResponse {
    private ESResponseHits hits = new ESResponseHits();

    public ESResponseHits getHits() {
        return hits;
    }

    public void setHits(ESResponseHits hits) {
        this.hits = hits;
    }
}
