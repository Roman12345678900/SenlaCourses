package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.ClassesDto;
import senla.com.entity.Classes;
import senla.com.exception.ClassesNotFoundException;
import senla.com.exception.UserNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.service.ClassesService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private final GenericRepository<Classes, Long> classesRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public ClassesDto findById(Long id) {
        Classes classes = classesRepository.findById(id).
                orElseThrow(() -> new ClassesNotFoundException(id));
        return genericMapper.convertToDto(classes, ClassesDto.class);
    }

    @Override
    @Transactional
    public List<ClassesDto> findAll() {
        return classesRepository.findAll().stream()
                .map(classes -> genericMapper.convertToDto(classes,ClassesDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(ClassesDto classesDto) {
        Classes classes = genericMapper.convertToEntity(classesDto, Classes.class);
        classesRepository.save(classes);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional.ofNullable(classesRepository.findById(id)
                .orElseThrow(() -> new ClassesNotFoundException(id)));
        classesRepository.deleteById(id);    }
}
