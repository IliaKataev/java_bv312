package com.example.domain;

import com.example.domain.Buildings.Building;

import java.util.ArrayList;
import java.util.List;

public class City {

    private final BrickStock stock;
    private final List<Building> buildings = new ArrayList<>();
    private final int maxBuildings;

    public City(int stock, int maxBuildings) {
        this.stock = new BrickStock(stock);
        this.maxBuildings = maxBuildings;
    }

    public void build(Building building){
        if(buildings.size() >= maxBuildings)
            throw new IllegalStateException("Город переполнен");
        stock.consume(building.getRequiredBricks());
        buildings.add(building);
    }

    public int getTotalBuilding(){
        return buildings.size();
    }
    public int getRemainBricks(){
        return stock.getAvailableBricks();
    }

    public List<Building> getBuildings(){
        return buildings;
    }
}
