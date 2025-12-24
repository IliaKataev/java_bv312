package com.example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CityProfileConfig {

    @Bean
    @Profile("dev")
    public City devCity(){
       return new City(10000, 50);
    }

    @Bean
    @Profile("prod")
    public City prodCity(){
        return new City(10000, 50);
    }
}
