package com.example.LegoCity.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "missions")
@Getter @Setter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean completed;
    private int rewardCubes;
    private int rewardMaxBuildings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("missions")
    private User user;

    @OneToMany(mappedBy = "mission", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("mission")
    private List<MissionRequirements> requirementsList;
}
