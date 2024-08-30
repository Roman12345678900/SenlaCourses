package senla.com.service;

import senla.com.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto findById(Long id);

    List<EquipmentDto> findAll();

    void save(EquipmentDto equipmentDto);

    void deleteById(Long id);

}
