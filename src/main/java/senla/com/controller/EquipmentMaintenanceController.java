package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.EquipmentMaintenanceDto;
import senla.com.service.EquipmentMaintenanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipment-maintenance")
public class EquipmentMaintenanceController {

    private final EquipmentMaintenanceService equipmentMaintenanceService;

    @GetMapping("/{id}")
    public EquipmentMaintenanceDto findById(@PathVariable("id") Long id) {
        return equipmentMaintenanceService.findById(id);
    }

    @GetMapping
    public List<EquipmentMaintenanceDto> findAll() {
        return equipmentMaintenanceService.findAll();
    }

    @PostMapping
    public void save(@RequestBody EquipmentMaintenanceDto equipmentMaintenanceDto) {
        equipmentMaintenanceService.save(equipmentMaintenanceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        equipmentMaintenanceService.deleteById(id);
    }
}
