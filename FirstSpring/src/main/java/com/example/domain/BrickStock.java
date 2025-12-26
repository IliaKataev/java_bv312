package com.example.domain;

public class BrickStock {

    private int availableBricks;

    public BrickStock(int availableBricks) {
        this.availableBricks = availableBricks;
    }

    public BrickStock() {
    }

    public int getAvailableBricks() {
        return availableBricks;
    }

    public boolean hasEnough(int req){
        return availableBricks >= req;
    }

    public void consume(int amount){
        if(!hasEnough(amount)){
            throw new IllegalStateException("Недостаточно кубиков");
        }
        availableBricks -= amount;
    }

    public void setAvailableBricks(int availableBricks) {
        this.availableBricks = availableBricks;
    }
}
