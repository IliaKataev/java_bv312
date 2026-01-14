package com.example.LegoCity.Service;

import com.example.LegoCity.Models.CityHistory;
import com.example.LegoCity.Repository.CityHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final CityHistoryRepository CHR;

    public void log(String action, String description){
        CityHistory history = new CityHistory();
        history.setAction(action);
        history.setDescription(description);
        history.setStampDate(LocalDateTime.now());
        CHR.save(history);
    }

    public List<CityHistory> getAll() {
        return CHR.findAll();
    }
}
