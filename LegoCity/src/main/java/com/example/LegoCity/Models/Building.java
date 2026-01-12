package com.example.LegoCity.Models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BuildingType type;

    @Column(name = "build_at")
    private LocalDateTime builtAt;

}
