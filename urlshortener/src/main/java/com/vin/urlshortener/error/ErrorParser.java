package com.vin.urlshortener.error;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by aravindp on 23/6/16.
 */
public class ErrorParser {

    private Retrofit retrofit;

    public ErrorParser(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public ErrorResponse parseResponse(Response<?> response) throws IOException {
        Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        ErrorResponse errorResponse = converter.convert(response.errorBody());
        return errorResponse;
    }
}
