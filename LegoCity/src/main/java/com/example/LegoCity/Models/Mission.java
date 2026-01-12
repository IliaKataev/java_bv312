package com.example.LegoCity.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean completed;
    private int rewardCubes;
    private int rewardMaxBuildings;

}
