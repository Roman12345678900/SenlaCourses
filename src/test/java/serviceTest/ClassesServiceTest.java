package serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.dto.ClassesDto;
import senla.com.entity.Classes;
import senla.com.entity.TrainerSchedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ClassesRepository;
import senla.com.service.implementation.ClassesServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class ClassesServiceTest {

    @Mock
    private ClassesRepository classesRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private ClassesServiceImpl classesService;

    @Before
    public void setUp() {
        classesRepository = Mockito.mock(ClassesRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        classesService = new ClassesServiceImpl(classesRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Classes clazz = new Classes();
        clazz.setId(id);
        clazz.setName("Yoga");
        clazz.setDescription("Yoga Class Description");

        when(classesRepository.findById(id)).thenReturn(Optional.of(clazz));

        ClassesDto clazzDto = new ClassesDto();
        clazzDto.setId(id);
        clazzDto.setName("Yoga");
        clazzDto.setDescription("Yoga Class Description");

        when(genericMapper.convertToDto(clazz, ClassesDto.class)).thenReturn(clazzDto);

        ClassesDto result = classesService.findById(id);

        verify(classesRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(clazz, ClassesDto.class);

        assertEquals(id, result.getId());
        assertEquals("Yoga", result.getName());
        assertEquals("Yoga Class Description", result.getDescription());
    }

    @Test
    public void testFindAll() {
        List<Classes> classesList = new ArrayList<>();
        classesList.add(new Classes(1L, "Yoga", "Yoga Class Description", null));
        classesList.add(new Classes(2L, "Pilates", "Pilates Class Description", null));

        when(classesRepository.findAll()).thenReturn(classesList);
        List<ClassesDto> result = classesService.findAll();

        assertEquals(classesList.size(), result.size());
        verify(classesRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Classes clazz = new Classes();
        clazz.setName("Yoga");
        clazz.setDescription("Yoga Class Description");

        ClassesDto clazzDto = new ClassesDto();
        clazzDto.setName("Yoga");
        clazzDto.setDescription("Yoga Class Description");

        when(genericMapper.convertToEntity(clazzDto, Classes.class)).thenReturn(clazz);

        classesService.save(clazzDto);

        verify(genericMapper, times(1)).convertToEntity(clazzDto, Classes.class);
        verify(classesRepository, times(1)).save(clazz);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        Classes classes = new Classes();
        classes.setId(id);

        when(classesRepository.findById(id)).thenReturn(Optional.of(classes));

        classesService.deleteById(id);

        verify(classesRepository, times(1)).deleteById(id);
    }
}
