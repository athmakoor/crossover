package com.crossover.imagesearch.bean;

import java.util.ArrayList;
import java.util.List;

public class ESResponseHits {
    private List<ESResponseHit> hits = new ArrayList<>();

    public List<ESResponseHit> getHits() {
        return hits;
    }

    public void setHits(List<ESResponseHit> hits) {
        this.hits = hits;
    }
}
