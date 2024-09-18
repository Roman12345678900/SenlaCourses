package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.SeasonTicketsTypeDto;
import senla.com.entity.SeasonTicketsType;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.SeasonTicketsTypeRepository;
import senla.com.service.SeasonTicketsTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonTicketsTypeImpl implements SeasonTicketsTypeService {

    private final GenericRepository<SeasonTicketsType, Long> seasonTicketsTypeRepository;
    private final GenericMapper genericMapper;

    @Override
    public SeasonTicketsTypeDto findById(Long id) {
        SeasonTicketsType seasonTicketsType = seasonTicketsTypeRepository.findById(id);
        return genericMapper.convertToDto(seasonTicketsType, SeasonTicketsTypeDto.class);
    }

    @Override
    public List<SeasonTicketsTypeDto> findAll() {
        return seasonTicketsTypeRepository.findAll().stream()
                .map(seasonTicketsType -> genericMapper.convertToDto(seasonTicketsType, SeasonTicketsTypeDto.class))
                .toList();
    }

    @Override
    public void save(SeasonTicketsTypeDto seasonTicketsTypeDto) {
        SeasonTicketsType seasonTicketsType = genericMapper.convertToEntity(seasonTicketsTypeDto, SeasonTicketsType.class);
        seasonTicketsTypeRepository.save(seasonTicketsType);
    }

    @Override
    public void deleteById(Long id) {
        seasonTicketsTypeRepository.deleteById(id);
    }
}
