package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.EquipmentDto;
import senla.com.entity.Equipment;
import senla.com.exception.EquipmentNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentRepository;
import senla.com.repository.GenericRepository;
import senla.com.service.EquipmentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final GenericRepository<Equipment, Long> equipmentRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public EquipmentDto findById(Long id) {
        Equipment equipment = equipmentRepository.findById(id).
                orElseThrow(() -> new EquipmentNotFoundException(id));
        return genericMapper.convertToDto(equipment, EquipmentDto.class);
    }

    @Override
    @Transactional
    public List<EquipmentDto> findAll() {
        return equipmentRepository.findAll().stream()
                .map(equipment -> genericMapper.convertToDto(equipment,EquipmentDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(EquipmentDto equipmentDto) {
        Equipment equipment = genericMapper.convertToEntity(equipmentDto, Equipment.class);
        equipmentRepository.save(equipment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id)));
        equipmentRepository.deleteById(id);    }
}
