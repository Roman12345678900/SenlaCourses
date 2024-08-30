package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.SeasonTicketsDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.SeasonTicketsService;

@Controller
@RequiredArgsConstructor
public class SeasonTickersController {

    private final SeasonTicketsService seasonTicketsService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(seasonTicketsService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(seasonTicketsService.findAll());
    }

    public void save(String seasonTicketsDto) {
        seasonTicketsService.save(jsonMapper.deserialize(seasonTicketsDto, SeasonTicketsDto.class));
    }

    public void deleteById(Long id) {
        seasonTicketsService.deleteById(id);
    }
}
