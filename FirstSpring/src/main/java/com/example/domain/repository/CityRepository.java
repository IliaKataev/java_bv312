package com.example.domain.repository;

import com.example.domain.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {
    private City city = new City(5000, 10);

    public City getCity(){
        return city;
    }
}
