package com.example.LegoCity.Repository;


import com.example.LegoCity.Models.Building;
import com.example.LegoCity.Models.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    long countByType(BuildingType type);
}


// SELECT COUNT(*) FROM BUILDINGS WHERE TYPE_ID = ?