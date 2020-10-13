package com.crossover.imagesearch.bean.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bool {
    private List<Map<String, Map<String, Object>>> must = new ArrayList<>();
    private List<Map<String, Map<String, String>>> filter = new ArrayList<>();

    public List<Map<String, Map<String, Object>>> getMust() {
        return must;
    }

    public void setMust(List<Map<String, Map<String, Object>>> must) {
        this.must = must;
    }

    public List<Map<String, Map<String, String>>> getFilter() {
        return filter;
    }

    public void setFilter(List<Map<String, Map<String, String>>> filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "Bool{" +
                "must=" + must +
                ", filter=" + filter +
                '}';
    }
}
