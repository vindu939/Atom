package com.vin.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aravindp on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Counts {
    private long count;
    private String id;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
