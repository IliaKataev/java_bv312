package com.example.domain.repository;

import com.example.domain.Buildings.House;
import com.example.domain.City;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Repository
public class CityRepository {

    private static final Path FILE = Paths.get("src/main/resources/city-data.json");

    private final ObjectMapper mapper;

    private City city;


    public CityRepository(ObjectMapper mapper) {
        this.mapper = mapper;
        city = mapper.readValue(FILE.toFile(), City.class);
    }

    public void save(){
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(FILE.toFile(), city);
    }

    public void buildHouse(){
        city.build(new House());
        save();
    }

    public City getCity(){
        return city;
    }

}
