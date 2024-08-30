package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.EquipmentDto;
import senla.com.entity.Equipment;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentRepository;
import senla.com.service.EquipmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final GenericMapper genericMapper;

    @Override
    public EquipmentDto findById(Long id) {
        Equipment equipment = equipmentRepository.findById(id);
        return genericMapper.convertToDto(equipment, EquipmentDto.class);
    }

    @Override
    public List<EquipmentDto> findAll() {
        return equipmentRepository.findAll().stream()
                .map(equipment -> genericMapper.convertToDto(equipment,EquipmentDto.class))
                .toList();
    }

    @Override
    public void save(EquipmentDto equipmentDto) {
        Equipment equipment = genericMapper.convertToEntity(equipmentDto, Equipment.class);
        equipmentRepository.save(equipment);
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }
}
