package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.SchedulesDto;

import java.util.List;

@Component
public interface SchedulesService {
    SchedulesDto findById(Long id);

    List<SchedulesDto> findAll();

    void save(SchedulesDto schedulesDto);

    void deleteById(Long id);
}
