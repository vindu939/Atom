package com.vin.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aravindp on 22/6/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlResources {
    private String kind;
    private String id;
    private String longUrl;
    private String status;
    private String created;
    private Analytics analytics;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }
}
