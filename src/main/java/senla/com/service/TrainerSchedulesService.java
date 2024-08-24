package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.TrainerSchedulesDto;

import java.util.List;

@Component
public interface TrainerSchedulesService {
    TrainerSchedulesDto findById(Long id);

    List<TrainerSchedulesDto> findAll();

    void save(TrainerSchedulesDto trainerSchedulesDto);

    void deleteById(Long id);
}
