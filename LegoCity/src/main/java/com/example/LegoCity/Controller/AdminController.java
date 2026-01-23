package com.example.LegoCity.Controller;

import com.example.LegoCity.Service.MissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final MissionService missionService;

    public AdminController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public String adminPage(){
        return "admin";
    }

    @PostMapping("/reset-missions")
    public String resetMissions(){
        missionService.resetAllUserMissions();
        return "redirect:/admin";
    }
}
