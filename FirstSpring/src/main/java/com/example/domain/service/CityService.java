package com.example.domain.service;

import com.example.domain.Buildings.House;
import com.example.domain.City;
import com.example.domain.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository repo;


    public CityService(CityRepository repo) {
        this.repo = repo;
    }

    public void buildHouse(){
        repo.buildHouse();
    }

    public City getCityState(){
        return repo.getCity();
    }
}
