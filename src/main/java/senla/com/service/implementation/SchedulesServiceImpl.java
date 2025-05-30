package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.SchedulesDto;
import senla.com.entity.Schedules;
import senla.com.exception.SchedulesNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.SchedulesRepository;
import senla.com.service.SchedulesService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulesServiceImpl implements SchedulesService {

    private final GenericRepository<Schedules, Long> schedulesRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public SchedulesDto findById(Long id) {
        Schedules schedules = schedulesRepository.findById(id).
                orElseThrow(() -> new SchedulesNotFoundException(id));
        return genericMapper.convertToDto(schedules, SchedulesDto.class);
    }

    @Override
    @Transactional
    public List<SchedulesDto> findAll() {
        return schedulesRepository.findAll().stream()
                .map(schedules -> genericMapper.convertToDto(schedules,SchedulesDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(SchedulesDto schedulesDto) {
        Schedules schedules = genericMapper.convertToEntity(schedulesDto, Schedules.class);
        schedulesRepository.save(schedules);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(schedulesRepository.findById(id)
                .orElseThrow(() -> new SchedulesNotFoundException(id)));
        schedulesRepository.deleteById(id);    }
}
