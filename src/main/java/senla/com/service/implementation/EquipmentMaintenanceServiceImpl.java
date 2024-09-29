package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.EquipmentMaintenanceDto;
import senla.com.entity.EquipmentMaintenance;
import senla.com.exception.EquipmentMaintenanceNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentMaintenanceRepository;
import senla.com.repository.GenericRepository;
import senla.com.service.EquipmentMaintenanceService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {

    private final GenericRepository<EquipmentMaintenance, Long> equipmentMaintenanceRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public EquipmentMaintenanceDto findById(Long id) {
        EquipmentMaintenance equipmentMaintenance = equipmentMaintenanceRepository.findById(id).
                orElseThrow(() -> new EquipmentMaintenanceNotFoundException(id));
        return genericMapper.convertToDto(equipmentMaintenance, EquipmentMaintenanceDto.class);
    }

    @Override
    @Transactional
    public List<EquipmentMaintenanceDto> findAll() {
        return equipmentMaintenanceRepository.findAll().stream()
                .map(equipmentMaintenance -> genericMapper.convertToDto(equipmentMaintenance, EquipmentMaintenanceDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(EquipmentMaintenanceDto equipmentMaintenanceDto) {
        EquipmentMaintenance equipmentMaintenance = genericMapper.convertToEntity(equipmentMaintenanceDto, EquipmentMaintenance.class);
        equipmentMaintenanceRepository.save(equipmentMaintenance);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(equipmentMaintenanceRepository.findById(id)
                .orElseThrow(() -> new EquipmentMaintenanceNotFoundException(id)));
        equipmentMaintenanceRepository.deleteById(id);    }
}
