package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.TrainerSchedulesDto;
import senla.com.entity.TrainerSchedules;
import senla.com.exception.TrainerSchedulesNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.TrainerSchedulesRepository;
import senla.com.service.TrainerSchedulesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerSchedulesServiceImpl implements TrainerSchedulesService {

    private final GenericRepository<TrainerSchedules, Long> trainerSchedulesRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public TrainerSchedulesDto findById(Long id) {
        TrainerSchedules trainerSchedules = trainerSchedulesRepository.findById(id).
                orElseThrow(() -> new TrainerSchedulesNotFoundException(id));
        return genericMapper.convertToDto(trainerSchedules, TrainerSchedulesDto.class);
    }

    @Override
    @Transactional
    public List<TrainerSchedulesDto> findAll() {
        return trainerSchedulesRepository.findAll().stream()
                .map(trainerSchedules -> genericMapper.convertToDto(trainerSchedules,TrainerSchedulesDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(TrainerSchedulesDto trainerSchedulesDto) {
        TrainerSchedules trainerSchedules = genericMapper.convertToEntity(trainerSchedulesDto,TrainerSchedules.class);
        trainerSchedulesRepository.save(trainerSchedules);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        trainerSchedulesRepository.deleteById(id);
    }
}
