package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.TrainerSchedules;
import senla.com.entity.User;
import senla.com.repository.implementation.TrainerSchedulesRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ApplicationConfiguration.class,
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class TrainerSchedulesRepositoryTest {

    @Resource
    TrainerSchedulesRepositoryImpl trainerSchedulesRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById(){
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .build();
        userRepository.save(user);

        TrainerSchedules schedule = TrainerSchedules.builder()
                .id(1L)
                .startTime(Timestamp.valueOf("2024-09-18 10:00:00"))
                .endTime(Timestamp.valueOf("2024-09-18 11:00:00"))
                .user(user)
                .build();

        trainerSchedulesRepository.save(schedule);

        TrainerSchedules foundSchedule = trainerSchedulesRepository.findById(schedule.getId());

        assertNotNull(foundSchedule);
        assertEquals(schedule.getId(), foundSchedule.getId());
    }

    @Test
    public void TestFindAll(){
        User user = User.builder()
                .id(2L)
                .firstName("Test")
                .lastName("Senla")
                .email("test2@test.com")
                .build();
        userRepository.save(user);

        TrainerSchedules schedule1 = TrainerSchedules.builder()
                .id(2L)
                .startTime(Timestamp.valueOf("2024-09-18 10:00:00"))
                .endTime(Timestamp.valueOf("2024-09-18 11:00:00"))
                .user(user)
                .build();

        TrainerSchedules schedule2 = TrainerSchedules.builder()
                .id(3L)
                .startTime(Timestamp.valueOf("2024-09-19 12:00:00"))
                .endTime(Timestamp.valueOf("2024-09-19 13:00:00"))
                .user(user)
                .build();

        trainerSchedulesRepository.save(schedule1);
        trainerSchedulesRepository.save(schedule2);

        List<TrainerSchedules> schedules = trainerSchedulesRepository.findAll();

        assertEquals(2, schedules.size());
    }

    @Test
    public void TestSave(){
        User user = User.builder()
                .id(4L)
                .firstName("Test")
                .lastName("Senla")
                .email("test4@test.com")
                .build();
        userRepository.save(user);

        TrainerSchedules schedule = TrainerSchedules.builder()
                .id(4L)
                .startTime(Timestamp.valueOf("2024-09-20 09:00:00"))
                .endTime(Timestamp.valueOf("2024-09-20 10:00:00"))
                .user(user)
                .build();

        trainerSchedulesRepository.save(schedule);

        TrainerSchedules savedSchedule = trainerSchedulesRepository.findById(schedule.getId());

        assertNotNull(savedSchedule);
        assertEquals(schedule.getStartTime(), savedSchedule.getStartTime());
    }

    @Test
    public void TestDelete(){
        User user = User.builder()
                .id(5L)
                .firstName("Test")
                .lastName("Senla")
                .email("test5@test.com")
                .build();
        userRepository.save(user);

        TrainerSchedules schedule = TrainerSchedules.builder()
                .id(5L)
                .startTime(Timestamp.valueOf("2024-09-21 14:00:00"))
                .endTime(Timestamp.valueOf("2024-09-21 15:00:00"))
                .user(user)
                .build();

        trainerSchedulesRepository.save(schedule);

        trainerSchedulesRepository.deleteById(schedule.getId());

        TrainerSchedules deletedSchedule = trainerSchedulesRepository.findById(schedule.getId());

        assertNull(deletedSchedule);
    }
}
