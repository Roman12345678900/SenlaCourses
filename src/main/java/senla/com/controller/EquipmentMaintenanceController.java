package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.EquipmentMaintenanceDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.EquipmentMaintenanceService;

@Controller
@RequiredArgsConstructor
public class EquipmentMaintenanceController {

    private final EquipmentMaintenanceService equipmentMaintenanceService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(equipmentMaintenanceService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(equipmentMaintenanceService.findAll());
    }

    public void save(String equipmentMaintenanceDto) {
        equipmentMaintenanceService.save(jsonMapper.deserialize(equipmentMaintenanceDto, EquipmentMaintenanceDto.class));
    }

    public void deleteById(Long id) {
        equipmentMaintenanceService.deleteById(id);
    }
}
