package com.example.LegoCity.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "city_history")
@Getter @Setter
public class CityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // BUILD DESTROY, MISSION

    private String description;

    @Column(name = "stamp_date")
    private LocalDateTime StampDate;
}
