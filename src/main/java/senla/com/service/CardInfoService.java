package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.CardInfoDto;

import java.util.List;

@Component
public interface CardInfoService {
    CardInfoDto findById(Long id);

    List<CardInfoDto> findAll();

    void save(CardInfoDto cardInfoDto);

    void deleteById(Long id);
}
