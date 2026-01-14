package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.BuildingType;
import com.example.LegoCity.Repository.BuildingTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building-types")
@RequiredArgsConstructor
public class BuildingTypeController {

    private final BuildingTypeRepository repository;

    @GetMapping
    public List<BuildingType> getAll() {
        return repository.findAll();
    }
}
