package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.ClassesDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.ClassesService;

@Controller
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(classesService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(classesService.findAll());
    }

    public void save(String classesDto) {
        classesService.save(jsonMapper.deserialize(classesDto, ClassesDto.class));
    }

    public void deleteById(Long id) {
        classesService.deleteById(id);
    }
}
