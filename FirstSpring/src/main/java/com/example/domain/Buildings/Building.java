package com.example.domain.Buildings;

public abstract class Building {

    private String name;
    private int requiredBricks;

    public Building(String name, int requiredBricks) {
        this.name = name;
        this.requiredBricks = requiredBricks;
    }

    public Building() {
    }

    public String getName() {
        return name;
    }

    public int getRequiredBricks() {
        return requiredBricks;
    }
}

