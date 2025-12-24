package com.example.domain;
import java.util.List;

public class CityResponseDTO {
    private int totalBuildings;
    private int remainingBricks;
    private List<String> buildings;

    public CityResponseDTO(int totalBuildings, int remainingBricks, List<String> buildings) {
        this.totalBuildings = totalBuildings;
        this.remainingBricks = remainingBricks;
        this.buildings = buildings;
    }

    public int getTotalBuildings() {
        return totalBuildings;
    }

    public void setTotalBuildings(int totalBuildings) {
        this.totalBuildings = totalBuildings;
    }

    public int getRemainingBricks() {
        return remainingBricks;
    }

    public void setRemainingBricks(int remainingBricks) {
        this.remainingBricks = remainingBricks;
    }

    public List<String> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<String> buildings) {
        this.buildings = buildings;
    }
}
