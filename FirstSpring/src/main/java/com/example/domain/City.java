package com.example.domain;

import com.example.domain.Buildings.Building;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class City {

    private BrickStock stock;
    private final List<Building> buildings = new ArrayList<>();
    private int maxBuildings;

    public City(int stock, int maxBuildings) {
        this.stock = new BrickStock(stock);
        this.maxBuildings = maxBuildings;
    }

    public City(){

    }

    public void build(Building building){
        if(buildings.size() >= maxBuildings)
            throw new IllegalStateException("Город переполнен");
        stock.consume(building.getRequiredBricks());
        buildings.add(building);
    }

    public int getMaxBuildings(){
        return buildings.size();
    }

    public int getStock(){
        return stock.getAvailableBricks();
    }

    @JsonIgnore
    public BrickStock getFullStock(){
        return stock;
    }

    public List<Building> getBuildings(){
        return buildings;
    }

}
