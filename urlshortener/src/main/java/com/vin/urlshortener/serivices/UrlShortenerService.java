package com.vin.urlshortener.serivices;

import com.vin.urlshortener.model.History;
import com.vin.urlshortener.model.UrlResources;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aravindp on 22/6/16.
 */
public interface UrlShortenerService {

    @POST("v1/url")
    public Call<UrlResources> shortenUrl(@Query("key") String key,
                                         @Body UrlResources urlResources);

    @GET("v1/url")
    public Call<UrlResources> shortUrlInfo(@Query("key") String key,
                             @Query("shortUrl") String shortUrl,
                             @Query("projection") String projection);

    @GET("v1/url/history")
    public Call<History> history(@Query("projection") String projection,
                           @Query("start-token") String startToken);
}
