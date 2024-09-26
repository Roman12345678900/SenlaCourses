package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.SchedulesDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.SchedulesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class SchedulesController {

    private final SchedulesService schedulesService;

    @GetMapping("/{id}")
    public SchedulesDto findById(@PathVariable("id") Long id) {
        return schedulesService.findById(id);
    }

    @GetMapping
    public List<SchedulesDto> findAll() {
        return schedulesService.findAll();
    }

    @PostMapping
    public void save(@RequestBody SchedulesDto schedulesDto) {
        schedulesService.save(schedulesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        schedulesService.deleteById(id);
    }
}
