package com.vin.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by aravindp on 22/6/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class History {
    private String kind;
    private int totalItems;
    private int itemsPerPage;
    private String nextPageToken;
    private List<UrlResources> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<UrlResources> getItems() {
        return items;
    }

    public void setItems(List<UrlResources> items) {
        this.items = items;
    }
}
