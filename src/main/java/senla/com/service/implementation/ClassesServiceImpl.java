package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.ClassesDto;
import senla.com.entity.Classes;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ClassesRepository;
import senla.com.service.ClassesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository classesRepository;
    private final GenericMapper genericMapper;

    @Override
    public ClassesDto findById(Long id) {
        return genericMapper.convertToDto(classesRepository.findById(id), ClassesDto.class);
    }

    @Override
    public List<ClassesDto> findAll() {
        return classesRepository.findAll().stream()
                .map(classes -> genericMapper.convertToDto(classes,ClassesDto.class))
                .toList();
    }

    @Override
    public void save(ClassesDto classesDto) {
        Classes classes = genericMapper.convertToEntity(classesDto, Classes.class);
        classesRepository.save(classes);
    }

    @Override
    public void deleteById(Long id) {
        classesRepository.deleteById(id);
    }
}
