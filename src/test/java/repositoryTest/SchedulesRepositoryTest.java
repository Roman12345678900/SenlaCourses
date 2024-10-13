package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Classes;
import senla.com.entity.Schedules;
import senla.com.entity.User;
import senla.com.repository.implementation.ClassesRepositoryImpl;
import senla.com.repository.implementation.SchedulesRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class SchedulesRepositoryTest {

    @Resource
    private SchedulesRepositoryImpl schedulesRepository;

    @Resource
    private ClassesRepositoryImpl classesRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById() {
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .password("1234")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(1L)
                .name("Yoga")
                .description("Yoga class for all levels")
                .users(user)
                .build();

        classesRepository.save(classes);

        Schedules schedule = Schedules.builder()
                .id(1L)
                .status("Active")
                .type("Class")
                .startTime(Timestamp.valueOf("2024-09-18 09:00:00"))
                .endTime(Timestamp.valueOf("2024-09-18 10:00:00"))
                .classes(classes)
                .build();

        schedulesRepository.save(schedule);

        Optional<Schedules> foundSchedule = schedulesRepository.findById(schedule.getId());

        assertNotNull(foundSchedule);
        assertEquals(schedule.getId(), foundSchedule.get().getId());
    }

    @Test
    public void TestFindAll() {
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .password("1234")
                .email("test@test.com")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(2L)
                .name("Pilates")
                .description("Pilates class for beginners")
                .users(user)
                .build();

        classesRepository.save(classes);

        Schedules schedule1 = Schedules.builder()
                .id(2L)
                .status("Active")
                .type("Class")
                .startTime(Timestamp.valueOf("2024-09-19 09:00:00"))
                .endTime(Timestamp.valueOf("2024-09-19 10:00:00"))
                .classes(classes)
                .build();

        Schedules schedule2 = Schedules.builder()
                .id(3L)
                .status("Inactive")
                .type("Class")
                .startTime(Timestamp.valueOf("2024-09-20 10:00:00"))
                .endTime(Timestamp.valueOf("2024-09-20 11:00:00"))
                .classes(classes)
                .build();

        schedulesRepository.save(schedule1);
        schedulesRepository.save(schedule2);

        List<Schedules> schedules = schedulesRepository.findAll();

        assertEquals(2, schedules.size());
    }

    @Test
    public void TestSave() {
        User user = User.builder()
                .id(1L)
                .password("1234")
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(4L)
                .name("Boxing")
                .description("Boxing class for advanced students")
                .users(user)
                .build();

        classesRepository.save(classes);

        Schedules schedule = Schedules.builder()
                .id(4L)
                .status("Active")
                .type("Class")
                .startTime(Timestamp.valueOf("2024-10-01 09:00:00"))
                .endTime(Timestamp.valueOf("2024-10-01 10:00:00"))
                .classes(classes)
                .build();

        schedulesRepository.save(schedule);

        Optional<Schedules> savedSchedule = schedulesRepository.findById(schedule.getId());

        assertNotNull(savedSchedule);
        assertEquals(schedule.getStartTime(), savedSchedule.get().getStartTime());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .password("1234")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(5L)
                .name("Swimming")
                .description("Swimming class for all levels")
                .users(user)
                .build();

        classesRepository.save(classes);

        Schedules schedule = Schedules.builder()
                .id(5L)
                .status("Active")
                .type("Class")
                .startTime(Timestamp.valueOf("2024-10-02 12:00:00"))
                .endTime(Timestamp.valueOf("2024-10-02 13:00:00"))
                .classes(classes)
                .build();

        schedulesRepository.save(schedule);

        schedulesRepository.deleteById(schedule.getId());

        Optional<Schedules> deletedSchedule = schedulesRepository.findById(schedule.getId());

        assertTrue(deletedSchedule.isEmpty());
    }
}
