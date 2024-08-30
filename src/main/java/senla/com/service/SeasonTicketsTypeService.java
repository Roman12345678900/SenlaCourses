package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.SeasonTicketsTypeDto;

import java.util.List;

@Component
public interface SeasonTicketsTypeService {
    SeasonTicketsTypeDto findById(Long id);

    List<SeasonTicketsTypeDto> findAll();

    void save(SeasonTicketsTypeDto seasonTicketsTypeDto);

    void deleteById(Long id);
}
