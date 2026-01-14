package com.example.LegoCity.Repository;

import com.example.LegoCity.Models.Mission;
import com.example.LegoCity.Models.MissionRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRequirementsRepository extends JpaRepository<MissionRequirements,Long> {
    MissionRequirements findByMission(Mission mission);
}
