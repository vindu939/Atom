package com.vin.urlshortener.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aravindp on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private Body error;

    public Body getError() {
        return error;
    }

    public void setError(Body error) {
        this.error = error;
    }
}
