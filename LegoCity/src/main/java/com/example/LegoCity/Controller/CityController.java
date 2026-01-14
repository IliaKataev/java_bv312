package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.CityState;
import com.example.LegoCity.Service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /**
     * Построить здание по типу
     */
    @PostMapping("/build/{typeId}")
    public void build(@PathVariable Long typeId) {
        cityService.build(typeId);
    }

    /**
     * Сброс города
     */
    @PostMapping("/reset")
    public void reset() {
        cityService.resetCity();
    }

    /**
     * Добавить кубики (награда / событие)
     */
    @PostMapping("/cubes/add")
    public void addCubes(@RequestParam int amount) {
        cityService.addCubes(amount);
    }

    /**
     * Получить состояние города
     */
    @GetMapping("/state")
    public CityState getState() {
        return cityService.getCityState();
    }
}
