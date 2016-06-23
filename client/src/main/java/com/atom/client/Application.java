package com.atom.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aravindp on 21/6/16.
 */
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
