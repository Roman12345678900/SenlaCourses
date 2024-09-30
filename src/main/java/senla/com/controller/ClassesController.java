package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.ClassesDto;
import senla.com.service.ClassesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class ClassesController {

    private final ClassesService classesService;

    @GetMapping("/{id}")
    public ClassesDto findById(@PathVariable("id") Long id) {
        return classesService.findById(id);
    }

    @GetMapping
    public List<ClassesDto> findAll() {
        return classesService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ClassesDto classesDto) {
        classesService.save(classesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        classesService.deleteById(id);
    }
}
