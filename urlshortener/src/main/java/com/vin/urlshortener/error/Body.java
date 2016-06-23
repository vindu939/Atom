package com.vin.urlshortener.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by aravindp on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {
    private List<ErrorModel> errors;
    private int code;
    private String message;

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
