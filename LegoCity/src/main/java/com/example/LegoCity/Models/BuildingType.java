package com.example.LegoCity.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "building_types")
@Getter @Setter
public class BuildingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int cubeCost;

    private String icon;

}
