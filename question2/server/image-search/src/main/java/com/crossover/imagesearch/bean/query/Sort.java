package com.crossover.imagesearch.bean.query;

import java.util.HashMap;
import java.util.Map;

public class Sort {
    private Map<String, String> id;

    public Sort() {
        Map<String, String> p = new HashMap<>();
        p.put("order", "desc");
        this.id = p;
    }

    public Map<String, String> getId() {
        return id;
    }

    public void setId(Map<String, String> id) {
        this.id = id;
    }
}
