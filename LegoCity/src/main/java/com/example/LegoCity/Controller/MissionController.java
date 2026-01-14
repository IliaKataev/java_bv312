package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.Mission;
import com.example.LegoCity.Service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /**
     * Получить все миссии
     */
    @GetMapping
    public List<Mission> getAll() {
        return missionService.getAllMissions();
    }

    /**
     * Принудительно проверить миссии (для тестов)
     */
    @PostMapping("/check")
    public void check() {
        missionService.checkMission();
    }
}
