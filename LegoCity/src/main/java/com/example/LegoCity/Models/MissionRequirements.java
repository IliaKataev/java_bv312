package com.example.LegoCity.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mission_requirements")
@Getter @Setter
public class MissionRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    @JsonIgnoreProperties("requirements")
    private Mission mission;

    @ManyToOne
    private BuildingType buildingType;

    private int requiredCount;
}
