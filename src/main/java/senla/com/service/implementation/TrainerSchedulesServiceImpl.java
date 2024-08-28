package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.TrainerSchedulesDto;
import senla.com.entity.TrainerSchedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.TrainerSchedulesRepository;
import senla.com.service.TrainerSchedulesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerSchedulesServiceImpl implements TrainerSchedulesService {

    private final TrainerSchedulesRepository trainerSchedulesRepository;
    private final GenericMapper genericMapper;

    @Override
    public TrainerSchedulesDto findById(Long id) {
        TrainerSchedules trainerSchedules = trainerSchedulesRepository.findById(id);
        return genericMapper.convertToDto(trainerSchedules, TrainerSchedulesDto.class);
    }

    @Override
    public List<TrainerSchedulesDto> findAll() {
        return trainerSchedulesRepository.findAll().stream()
                .map(trainerSchedules -> genericMapper.convertToDto(trainerSchedules,TrainerSchedulesDto.class))
                .toList();
    }

    @Override
    public void save(TrainerSchedulesDto trainerSchedulesDto) {
        TrainerSchedules trainerSchedules = genericMapper.convertToEntity(trainerSchedulesDto,TrainerSchedules.class);
        trainerSchedulesRepository.save(trainerSchedules);
    }

    @Override
    public void deleteById(Long id) {
        trainerSchedulesRepository.deleteById(id);
    }
}
