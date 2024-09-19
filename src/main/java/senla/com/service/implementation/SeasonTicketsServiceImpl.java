package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.SeasonTicketsDto;
import senla.com.entity.SeasonTickets;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.service.SeasonTicketsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonTicketsServiceImpl implements SeasonTicketsService {

    private final GenericRepository<SeasonTickets, Long> seasonTicketsRepository;
    private final GenericMapper genericMapper;

    @Override
    public SeasonTicketsDto findById(Long id) {
        SeasonTickets seasonTickets = seasonTicketsRepository.findById(id);
        return genericMapper.convertToDto(seasonTickets, SeasonTicketsDto.class);
    }

    @Override
    public List<SeasonTicketsDto> findAll() {
        return seasonTicketsRepository.findAll().stream()
                .map(seasonTickets -> genericMapper.convertToDto(seasonTickets,SeasonTicketsDto.class))
                .toList();
    }

    @Override
    public void save(SeasonTicketsDto seasonTicketsDto) {
        SeasonTickets seasonTickets = genericMapper.convertToEntity(seasonTicketsDto, SeasonTickets.class);
        seasonTicketsRepository.save(seasonTickets);
    }

    @Override
    public void deleteById(Long id) {
        seasonTicketsRepository.deleteById(id);
    }
}
