package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.TrainerSchedulesDto;
import senla.com.service.TrainerSchedulesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainer-schedules")
public class TrainerSchedulesController {

    private final TrainerSchedulesService trainerSchedulesService;

    @GetMapping("/{id}")
    public TrainerSchedulesDto findById(@PathVariable("id") Long id) {
        return trainerSchedulesService.findById(id);
    }

    @GetMapping
    public List<TrainerSchedulesDto> findAll() {
        return trainerSchedulesService.findAll();
    }

    @PostMapping
    public void save(@RequestBody TrainerSchedulesDto trainerSchedulesDto) {
        trainerSchedulesService.save(trainerSchedulesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        trainerSchedulesService.deleteById(id);
    }
}
