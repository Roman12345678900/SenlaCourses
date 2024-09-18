package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.SchedulesDto;
import senla.com.entity.Schedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.SchedulesRepository;
import senla.com.service.SchedulesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulesServiceImpl implements SchedulesService {

    private final GenericRepository<Schedules, Long> schedulesRepository;
    private final GenericMapper genericMapper;

    @Override
    public SchedulesDto findById(Long id) {
        Schedules schedules = schedulesRepository.findById(id);
        return genericMapper.convertToDto(schedules, SchedulesDto.class);
    }

    @Override
    public List<SchedulesDto> findAll() {
        return schedulesRepository.findAll().stream()
                .map(schedules -> genericMapper.convertToDto(schedules,SchedulesDto.class))
                .toList();
    }

    @Override
    public void save(SchedulesDto schedulesDto) {
        Schedules schedules = genericMapper.convertToEntity(schedulesDto, Schedules.class);
        schedulesRepository.save(schedules);
    }

    @Override
    public void deleteById(Long id) {
        schedulesRepository.deleteById(id);
    }
}
