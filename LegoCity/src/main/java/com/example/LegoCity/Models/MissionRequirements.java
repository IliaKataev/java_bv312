package com.example.LegoCity.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "mission_requirements")
public class MissionRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mission mission;

    @ManyToOne
    private BuildingType buildingType;
}
