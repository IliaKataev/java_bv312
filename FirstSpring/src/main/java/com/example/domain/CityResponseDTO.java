package com.example.domain;
import java.util.List;

public class CityResponseDTO {
    private int maxBuildings;
    private int stock;
    private List<String> buildings;

    public CityResponseDTO(int totalBuildings, int remainingBricks, List<String> buildings) {
        this.maxBuildings = totalBuildings;
        this.stock = remainingBricks;
        this.buildings = buildings;
    }

    public int getMaxBuildings() {
        return maxBuildings;
    }

    public void setMaxBuildings(int maxBuildings) {
        this.maxBuildings = maxBuildings;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<String> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<String> buildings) {
        this.buildings = buildings;
    }
}
