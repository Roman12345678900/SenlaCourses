package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.CardInfoDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.CardInfoService;

@Controller
@RequiredArgsConstructor
public class CardInfoController {

    private final CardInfoService cardInfoService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(cardInfoService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(cardInfoService.findAll());
    }

    public void save(String cardInfoDto) {
        cardInfoService.save(jsonMapper.deserialize(cardInfoDto, CardInfoDto.class));
    }

    public void deleteById(Long id) {
        cardInfoService.deleteById(id);
    }
}
