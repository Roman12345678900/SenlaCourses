package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.CardInfoDto;
import senla.com.entity.CardInfo;
import senla.com.mapper.GenericMapper;
import senla.com.repository.CardInfoRepository;
import senla.com.repository.GenericRepository;
import senla.com.service.CardInfoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardInfoServiceImpl implements CardInfoService {

    private final GenericRepository<CardInfo, Long> cardInfoRepository;
    private final GenericMapper genericMapper;

    @Override
    public CardInfoDto findById(Long id) {
        CardInfo cardInfo = cardInfoRepository.findById(id);
        return genericMapper.convertToDto(cardInfo,CardInfoDto.class);
    }

    @Override
    public List<CardInfoDto> findAll() {
        return cardInfoRepository.findAll()
                .stream()
                .map(cardInfo -> genericMapper.convertToDto(cardInfo, CardInfoDto.class))
                .toList();
    }

    @Override
    public void save(CardInfoDto cardInfoDto) {
        CardInfo cardInfo = genericMapper.convertToEntity(cardInfoDto, CardInfo.class);
        cardInfoRepository.save(cardInfo);
    }

    @Override
    public void deleteById(Long id) {
        cardInfoRepository.deleteById(id);
    }
}
