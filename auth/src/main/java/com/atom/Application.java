package com.atom;

import com.atom.auth.AuthConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aravindp on 21/6/16.
 */
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${redirectUrl}")
    private String redirectUrl;

    @Bean
    public AuthConfiguration authConfiguration(){

        List<String> scopes = new ArrayList<>();
        scopes.add("request");
        scopes.add("request_receipt");
        scopes.add("all_trips");
        scopes.add("profile");
        scopes.add("places");
        scopes.add("ride_widgets");
        scopes.add("history");
        scopes.add("history_Lite");

        return new AuthConfiguration.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUrl)
                .setScopes(scopes)
                .build();
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
