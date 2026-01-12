package com.example.LegoCity.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "city_state")
public class CityState {
    @Id
    private Long id = 1L;

    private int cubes;

    private int maxBuildings;
}
