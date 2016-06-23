package com.vin.urlshortener.error;

import retrofit2.Response;

/**
 * Created by aravindp on 22/6/16.
 */
public class ErrorUtils {

    public static ErrorResponse parseError(Response<?> response) {
       /* Converter<ResponseBody, ErrorResponse> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError com.vin.urlshortener.error;

        try {
            com.vin.urlshortener.error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return com.vin.urlshortener.error;*/
        return null;
    }
}
