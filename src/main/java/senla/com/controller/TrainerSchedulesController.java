package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.TrainerSchedulesDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.TrainerSchedulesService;

@Controller
@RequiredArgsConstructor
public class TrainerSchedulesController {

    private final TrainerSchedulesService trainerSchedulesService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(trainerSchedulesService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(trainerSchedulesService.findAll());
    }

    public void save(String trainerSchedulesDto) {
        trainerSchedulesService.save(jsonMapper.deserialize(trainerSchedulesDto, TrainerSchedulesDto.class));
    }

    public void deleteById(Long id) {
        trainerSchedulesService.deleteById(id);
    }
}
