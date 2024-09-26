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
import senla.com.dto.SchedulesDto;
import senla.com.entity.Classes;
import senla.com.entity.Schedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.SchedulesRepository;
import senla.com.service.implementation.SchedulesServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class SchedulesServiceTest {

    @Mock
    private SchedulesRepository schedulesRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private SchedulesServiceImpl schedulesService;

    @Before
    public void setUp() {
        schedulesRepository = Mockito.mock(SchedulesRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        schedulesService = new SchedulesServiceImpl(schedulesRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Schedules schedule = new Schedules();
        schedule.setId(id);
        schedule.setStatus("Active");
        schedule.setType("Lecture");
        schedule.setStartTime(Timestamp.valueOf("2024-09-25 09:00:00"));
        schedule.setEndTime(Timestamp.valueOf("2024-09-25 11:00:00"));

        Classes classes = new Classes();
        schedule.setClasses(classes);

        when(schedulesRepository.findById(id)).thenReturn(Optional.of(schedule));

        SchedulesDto scheduleDto = new SchedulesDto();
        scheduleDto.setId(id);
        scheduleDto.setStatus("Active");
        scheduleDto.setType("Lecture");
        scheduleDto.setStartTime(Timestamp.valueOf("2024-09-25 09:00:00"));
        scheduleDto.setEndTime(Timestamp.valueOf("2024-09-25 11:00:00"));
        when(genericMapper.convertToDto(schedule, SchedulesDto.class)).thenReturn(scheduleDto);

        SchedulesDto result = schedulesService.findById(id);

        verify(schedulesRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(schedule, SchedulesDto.class);

        assertEquals(id, result.getId());
        assertEquals(schedule.getStatus(), result.getStatus());
        assertEquals(schedule.getType(), result.getType());
    }

    @Test
    public void testFindAll() {
        List<Schedules> schedulesList = new ArrayList<>();
        schedulesList.add(new Schedules(1L, "Active", "Lecture",
                Timestamp.valueOf("2024-09-25 09:00:00"),
                Timestamp.valueOf("2024-09-25 11:00:00"), null, null));
        schedulesList.add(new Schedules(2L, "Inactive", "Seminar",
                Timestamp.valueOf("2024-09-26 09:00:00"),
                Timestamp.valueOf("2024-09-26 11:00:00"), null, null));

        when(schedulesRepository.findAll()).thenReturn(schedulesList);
        List<SchedulesDto> result = schedulesService.findAll();

        assertEquals(schedulesList.size(), result.size());
        verify(schedulesRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Schedules schedule = new Schedules();
        schedule.setStatus("Active");
        schedule.setType("Lecture");
        schedule.setStartTime(Timestamp.valueOf("2024-09-25 09:00:00"));
        schedule.setEndTime(Timestamp.valueOf("2024-09-25 11:00:00"));

        Classes classes = new Classes(); // инициализируйте класс Classes
        schedule.setClasses(classes);

        SchedulesDto scheduleDto = new SchedulesDto();
        scheduleDto.setStatus("Active");
        scheduleDto.setType("Lecture");
        scheduleDto.setStartTime(Timestamp.valueOf("2024-09-25 09:00:00"));
        scheduleDto.setEndTime(Timestamp.valueOf("2024-09-25 11:00:00"));

        when(genericMapper.convertToEntity(scheduleDto, Schedules.class)).thenReturn(schedule);

        schedulesService.save(scheduleDto);

        verify(genericMapper, times(1)).convertToEntity(scheduleDto, Schedules.class);
        verify(schedulesRepository, times(1)).save(schedule);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        schedulesService.deleteById(id);
        verify(schedulesRepository, times(1)).deleteById(id);
    }
}
