package com.example.LegoCity.Repository;

import com.example.LegoCity.Models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
