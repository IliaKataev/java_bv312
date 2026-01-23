package com.example.LegoCity.Repository;

import com.example.LegoCity.Models.Mission;
import com.example.LegoCity.Models.MissionRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRequirementsRepository extends JpaRepository<MissionRequirements,Long> {
    List<MissionRequirements> findAllByMission(Mission mission);
}
