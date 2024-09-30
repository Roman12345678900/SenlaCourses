package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.EquipmentDto;
import senla.com.service.EquipmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("/{id}")
    public EquipmentDto findById(@PathVariable("id") Long id) {
        return equipmentService.findById(id);
    }

    @GetMapping
    public List<EquipmentDto> findAll() {
        return equipmentService.findAll();
    }

    @PostMapping
    public void save(@RequestBody EquipmentDto equipmentDto) {
        equipmentService.save(equipmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        equipmentService.deleteById(id);
    }
}
