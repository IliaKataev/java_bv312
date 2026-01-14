package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.CityHistory;
import com.example.LegoCity.Repository.CityHistoryRepository;
import com.example.LegoCity.Service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService ;

    /**
     * Вся история города
     */
    @GetMapping
    public List<CityHistory> getAll() {
        return historyService.getAll();
    }
}
