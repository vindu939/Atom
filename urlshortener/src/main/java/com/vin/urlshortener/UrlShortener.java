package com.vin.urlshortener;

import com.squareup.moshi.Moshi;
import com.vin.urlshortener.error.ErrorParser;
import com.vin.urlshortener.error.ErrorResponse;
import com.vin.urlshortener.model.UrlResources;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import com.vin.urlshortener.serivices.UrlShortenerService;
import com.vin.urlshortener.utils.PrimitiveAdapter;

import java.io.IOException;

/**
 * Created by aravindp on 22/6/16.
 */
public class UrlShortener {

    private String key;
    private static Retrofit retrofit;
    private static String baseUrl = "https://www.googleapis.com/urlshortener/";
    private static UrlShortenerService shortenerService;
    private static ErrorParser errorParser;

    static {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().build();

        Moshi moshi = new Moshi.Builder().add(new PrimitiveAdapter()).build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseUrl)
                .client(client)
                .build();

        errorParser = new ErrorParser(retrofit);

        shortenerService = retrofit.create(UrlShortenerService.class);
    }

    public UrlShortener(String API_KEY){
        this.key = API_KEY;
    }

    public String getShortUrl(String longUrl) throws IOException {
        UrlResources resources = new UrlResources();
        resources.setLongUrl(longUrl);

        Response<UrlResources> response = shortenerService.shortenUrl(this.key, resources).execute();

        if (response.body() == null && response.errorBody() != null){
            ErrorResponse errorResponse = errorParser.parseResponse(response);
            throw new IOException(errorResponse.getError().getMessage());
        }
        resources = response.body();
        return resources.getId();
    }
}
