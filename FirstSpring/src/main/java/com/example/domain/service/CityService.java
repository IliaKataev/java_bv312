package com.example.domain.service;

import com.example.domain.Buildings.Building;
import com.example.domain.Buildings.House;
import com.example.domain.Buildings.PoliceStation;
import com.example.domain.Buildings.School;
import com.example.domain.City;
import com.example.domain.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository repo;


    public CityService(CityRepository repo) {
        this.repo = repo;
    }

    public void build(String type){
        City city = repo.getCity();

        Building building;
        switch(type.toLowerCase()){
            case "house" -> building = new House();
            case "school" -> building = new School();
            case "police" -> building = new PoliceStation();
            default -> throw new IllegalArgumentException("Неизвестный тип здания");
        }
        city.build(building);
        repo.save();
    }

    public void addBricks(int amount){
        City city = repo.getCity();
        city.getFullStock().setAvailableBricks(city.getFullStock().getAvailableBricks() + amount);
        repo.save();
    }

    public City getCityState(){
        return repo.getCity();
    }



}
