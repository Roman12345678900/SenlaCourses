package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.CardInfoDto;
import senla.com.entity.CardInfo;
import senla.com.exception.CardInfoNotFoundException;
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
    @Transactional
    public CardInfoDto findById(Long id) {
        CardInfo cardInfo = cardInfoRepository.findById(id).
                orElseThrow(() -> new CardInfoNotFoundException(id));
        return genericMapper.convertToDto(cardInfo,CardInfoDto.class);
    }

    @Override
    @Transactional
    public List<CardInfoDto> findAll() {
        return cardInfoRepository.findAll()
                .stream()
                .map(cardInfo -> genericMapper.convertToDto(cardInfo, CardInfoDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(CardInfoDto cardInfoDto) {
        CardInfo cardInfo = genericMapper.convertToEntity(cardInfoDto, CardInfo.class);
        cardInfoRepository.save(cardInfo);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cardInfoRepository.deleteById(id);
    }
}
