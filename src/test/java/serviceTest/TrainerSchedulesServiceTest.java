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
import senla.com.dto.TrainerSchedulesDto;
import senla.com.entity.TrainerSchedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.TrainerSchedulesRepository;
import senla.com.service.implementation.TrainerSchedulesServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class TrainerSchedulesServiceTest {

    @Mock
    private TrainerSchedulesRepository trainerSchedulesRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private TrainerSchedulesServiceImpl trainerSchedulesService;

    @Before
    public void setUp() {
        trainerSchedulesRepository = Mockito.mock(TrainerSchedulesRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        trainerSchedulesService = new TrainerSchedulesServiceImpl(trainerSchedulesRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        TrainerSchedules schedule = new TrainerSchedules();
        schedule.setId(id);
        when(trainerSchedulesRepository.findById(id)).thenReturn(Optional.of(schedule));

        TrainerSchedulesDto scheduleDto = new TrainerSchedulesDto();
        scheduleDto.setId(id);
        when(genericMapper.convertToDto(schedule, TrainerSchedulesDto.class)).thenReturn(scheduleDto);

        TrainerSchedulesDto result = trainerSchedulesService.findById(id);

        verify(trainerSchedulesRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(schedule, TrainerSchedulesDto.class);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindAll() {
        List<TrainerSchedules> schedules = new ArrayList<>();
        schedules.add(new TrainerSchedules());
        when(trainerSchedulesRepository.findAll()).thenReturn(schedules);
        List<TrainerSchedulesDto> result = trainerSchedulesService.findAll();

        assertEquals(schedules.size(), result.size());
        verify(trainerSchedulesRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        TrainerSchedules schedule = new TrainerSchedules();
        schedule.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));

        TrainerSchedulesDto scheduleDto = new TrainerSchedulesDto();
        scheduleDto.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));

        when(genericMapper.convertToEntity(scheduleDto, TrainerSchedules.class)).thenReturn(schedule);

        trainerSchedulesService.save(scheduleDto);

        verify(genericMapper, times(1)).convertToEntity(scheduleDto, TrainerSchedules.class);
        verify(trainerSchedulesRepository, times(1)).save(schedule);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        TrainerSchedules trainerSchedules = new TrainerSchedules();
        trainerSchedules.setId(id);

        when(trainerSchedulesRepository.findById(id)).thenReturn(Optional.of(trainerSchedules));

        trainerSchedulesService.deleteById(id);

        verify(trainerSchedulesRepository, times(1)).deleteById(id);
    }
}
