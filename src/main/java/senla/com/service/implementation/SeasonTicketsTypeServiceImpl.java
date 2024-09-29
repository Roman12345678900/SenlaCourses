package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.SeasonTicketsTypeDto;
import senla.com.entity.SeasonTicketsType;
import senla.com.exception.SeasonTicketsTypeNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.service.SeasonTicketsTypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonTicketsTypeServiceImpl implements SeasonTicketsTypeService {

    private final GenericRepository<SeasonTicketsType, Long> seasonTicketsTypeRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public SeasonTicketsTypeDto findById(Long id) {
        SeasonTicketsType seasonTicketsType = seasonTicketsTypeRepository.findById(id).
                orElseThrow(() -> new SeasonTicketsTypeNotFoundException(id));
        return genericMapper.convertToDto(seasonTicketsType, SeasonTicketsTypeDto.class);
    }

    @Override
    @Transactional
    public List<SeasonTicketsTypeDto> findAll() {
        return seasonTicketsTypeRepository.findAll().stream()
                .map(seasonTicketsType -> genericMapper.convertToDto(seasonTicketsType, SeasonTicketsTypeDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(SeasonTicketsTypeDto seasonTicketsTypeDto) {
        SeasonTicketsType seasonTicketsType = genericMapper.convertToEntity(seasonTicketsTypeDto, SeasonTicketsType.class);
        seasonTicketsTypeRepository.save(seasonTicketsType);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(seasonTicketsTypeRepository.findById(id)
                .orElseThrow(() -> new SeasonTicketsTypeNotFoundException(id)));
        seasonTicketsTypeRepository.deleteById(id);
    }
}
