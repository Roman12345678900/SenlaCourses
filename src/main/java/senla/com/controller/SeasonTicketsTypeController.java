package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.SeasonTicketsTypeDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.SeasonTicketsTypeService;

@Controller
@RequiredArgsConstructor
public class SeasonTicketsTypeController {

    private final SeasonTicketsTypeService seasonTicketsTypeService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(seasonTicketsTypeService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(seasonTicketsTypeService.findAll());
    }

    public void save(String seasonTicketsTypeDto) {
        seasonTicketsTypeService.save(jsonMapper.deserialize(seasonTicketsTypeDto, SeasonTicketsTypeDto.class));
    }

    public void deleteById(Long id) {

    }
}
