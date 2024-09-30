package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.SeasonTicketsTypeDto;
import senla.com.service.SeasonTicketsTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/season-tickets-types")
@RequiredArgsConstructor
public class SeasonTicketsTypeController {

    private final SeasonTicketsTypeService seasonTicketsTypeService;

    @GetMapping("/{id}")
    public SeasonTicketsTypeDto findById(@PathVariable("id") Long id) {
        return seasonTicketsTypeService.findById(id);
    }

    @GetMapping
    public List<SeasonTicketsTypeDto> findAll() {
        return seasonTicketsTypeService.findAll();
    }

    @PostMapping
    public void save(@RequestBody SeasonTicketsTypeDto seasonTicketsTypeDto) {
        seasonTicketsTypeService.save(seasonTicketsTypeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        seasonTicketsTypeService.deleteById(id);
    }
}
