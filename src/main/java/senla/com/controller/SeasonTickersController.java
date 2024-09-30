package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.SeasonTicketsDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.SeasonTicketsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/season-tickets")
@RequiredArgsConstructor
public class SeasonTickersController {

    private final SeasonTicketsService seasonTicketsService;

    @GetMapping("/{id}")
    public SeasonTicketsDto findById(@PathVariable("id") Long id) {
        return seasonTicketsService.findById(id);
    }

    @GetMapping
    public List<SeasonTicketsDto> findAll() {
        return seasonTicketsService.findAll();
    }

    @PostMapping
    public void save(@RequestBody SeasonTicketsDto seasonTicketsDto) {
        seasonTicketsService.save(seasonTicketsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        seasonTicketsService.deleteById(id);
    }
}
