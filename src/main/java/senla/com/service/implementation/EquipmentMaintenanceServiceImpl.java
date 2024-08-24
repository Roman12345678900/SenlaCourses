package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.EquipmentMaintenanceDto;
import senla.com.entity.EquipmentMaintenance;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentMaintenanceRepository;
import senla.com.service.EquipmentMaintenanceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {

    private final EquipmentMaintenanceRepository equipmentMaintenanceRepository;
    private final GenericMapper genericMapper;

    @Override
    public EquipmentMaintenanceDto findById(Long id) {
        return genericMapper.convertToDto(equipmentMaintenanceRepository.findById(id), EquipmentMaintenanceDto.class);
    }

    @Override
    public List<EquipmentMaintenanceDto> findAll() {
        return equipmentMaintenanceRepository.findAll().stream()
                .map(equipmentMaintenance -> genericMapper.convertToDto(equipmentMaintenance, EquipmentMaintenanceDto.class))
                .toList();
    }

    @Override
    public void save(EquipmentMaintenanceDto equipmentMaintenanceDto) {
        EquipmentMaintenance equipmentMaintenance = genericMapper.convertToEntity(equipmentMaintenanceDto, EquipmentMaintenance.class);
        equipmentMaintenanceRepository.save(equipmentMaintenance);
    }

    @Override
    public void deleteById(Long id) {
        equipmentMaintenanceRepository.deleteById(id);
    }
}
