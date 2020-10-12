package com.crossover.imagesearch.bean.query;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Query {
    private Bool bool = new Bool();

    public Bool getBool() {
        return bool;
    }

    public void setBool(Bool bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return "Query{" +
                "bool=" + bool +
                '}';
    }
}
