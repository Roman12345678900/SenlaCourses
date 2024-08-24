package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.ClassesDto;

import java.util.List;

@Component
public interface ClassesService {
    ClassesDto findById(Long id);

    List<ClassesDto> findAll();

    void save(ClassesDto classesDto);

    void deleteById(Long id);
}
