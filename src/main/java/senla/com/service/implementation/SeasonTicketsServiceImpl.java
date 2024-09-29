package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.SeasonTicketsDto;
import senla.com.entity.SeasonTickets;
import senla.com.exception.SeasonTicketsNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.service.SeasonTicketsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonTicketsServiceImpl implements SeasonTicketsService {

    private final GenericRepository<SeasonTickets, Long> seasonTicketsRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public SeasonTicketsDto findById(Long id) {
        SeasonTickets seasonTickets = seasonTicketsRepository.findById(id).
                orElseThrow(() -> new SeasonTicketsNotFoundException(id));
        return genericMapper.convertToDto(seasonTickets, SeasonTicketsDto.class);
    }

    @Override
    @Transactional
    public List<SeasonTicketsDto> findAll() {
        return seasonTicketsRepository.findAll().stream()
                .map(seasonTickets -> genericMapper.convertToDto(seasonTickets,SeasonTicketsDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(SeasonTicketsDto seasonTicketsDto) {
        SeasonTickets seasonTickets = genericMapper.convertToEntity(seasonTicketsDto, SeasonTickets.class);
        seasonTicketsRepository.save(seasonTickets);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(seasonTicketsRepository.findById(id)
                .orElseThrow(() -> new SeasonTicketsNotFoundException(id)));
        seasonTicketsRepository.deleteById(id);    }
}
