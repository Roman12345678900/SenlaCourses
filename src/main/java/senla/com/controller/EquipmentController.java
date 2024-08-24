package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.EquipmentDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.EquipmentService;

@Controller
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(equipmentService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(equipmentService.findAll());
    }

    public void save(String equipmentDto) {
        equipmentService.save(jsonMapper.deserialize(equipmentDto, EquipmentDto.class));
    }

    public void deleteById(Long id) {
        equipmentService.deleteById(id);
    }
}
