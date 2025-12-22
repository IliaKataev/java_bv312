package com.example.domain.Buildings;

public abstract class Building {

    private final String name;
    private final int requiredBricks;

    public Building(String name, int requiredBricks) {
        this.name = name;
        this.requiredBricks = requiredBricks;
    }

    public String getName() {
        return name;
    }

    public int getRequiredBricks() {
        return requiredBricks;
    }
}

