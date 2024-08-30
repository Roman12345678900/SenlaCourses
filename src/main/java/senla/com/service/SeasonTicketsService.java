package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.SeasonTicketsDto;

import java.util.List;

@Component
public interface SeasonTicketsService {
    SeasonTicketsDto findById(Long id);

    List<SeasonTicketsDto> findAll();

    void save(SeasonTicketsDto seasonTicketsDto);

    void deleteById(Long id);
}
