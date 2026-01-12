package com.example.LegoCity.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "building_types")
public class BuildingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int cubeCost;

    private String icon;

}
