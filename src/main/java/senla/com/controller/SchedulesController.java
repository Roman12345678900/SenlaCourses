package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.SchedulesDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.SchedulesService;

@Controller
@RequiredArgsConstructor
public class SchedulesController {

    private final SchedulesService schedulesService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(schedulesService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(schedulesService.findAll());
    }

    public void save(String schedulesDto) {
        schedulesService.save(jsonMapper.deserialize(schedulesDto, SchedulesDto.class));
    }

    public void deleteById(Long id) {
        schedulesService.deleteById(id);
    }
}
