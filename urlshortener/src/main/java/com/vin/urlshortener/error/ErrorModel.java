package com.vin.urlshortener.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aravindp on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorModel {
    private String domain;
    private String reason;
    private String message;
    private String locationType;
    private String location;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
