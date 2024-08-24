package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.EquipmentMaintenanceDto;

import java.util.List;

@Component
public interface EquipmentMaintenanceService {
    EquipmentMaintenanceDto findById(Long id);

    List<EquipmentMaintenanceDto> findAll();

    void save(EquipmentMaintenanceDto equipmentMaintenanceDto);

    void deleteById(Long id);
}

