package com.example.LegoCity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LegoCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegoCityApplication.class, args);
    }

}
