package com.example.domain.controller;

import com.example.domain.City;
import com.example.domain.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService service;

    public CityController(CityService service){
        this.service=service;
    }

    @PostMapping("/build/house")
    public void buildHouse(){
        service.buildHouse();
    }

    @GetMapping
    public City getCity(){
        return service.getCityState();
    }
}
