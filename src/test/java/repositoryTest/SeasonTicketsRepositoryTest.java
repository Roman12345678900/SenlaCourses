package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.SeasonTickets;
import senla.com.entity.SeasonTicketsType;
import senla.com.entity.User;
import senla.com.repository.implementation.SeasonTicketsRepositoryImpl;
import senla.com.repository.implementation.SeasonTicketsTypeRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class SeasonTicketsRepositoryTest {

    @Resource
    private SeasonTicketsRepositoryImpl seasonTicketsRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Resource
    private SeasonTicketsTypeRepositoryImpl seasonTicketsTypeRepository;

    @Test
    public void TestFindById() {
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .password("1234")
                .email("test@test.com")
                .build();
        userRepository.save(user);

        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(1L)
                .name("Monthly")
                .price(BigDecimal.valueOf(123))
                .build();
        seasonTicketsTypeRepository.save(seasonTicketsType);

        SeasonTickets seasonTicket = SeasonTickets.builder()
                .id(1L)
                .startTime(Timestamp.valueOf("2024-09-01 00:00:00"))
                .endTime(Timestamp.valueOf("2024-10-01 00:00:00"))
                .user(user)
                .seasonTicketsType(seasonTicketsType)
                .build();
        seasonTicketsRepository.save(seasonTicket);

        Optional<SeasonTickets> foundTicket = seasonTicketsRepository.findById(seasonTicket.getId());

        assertNotNull(foundTicket);
        assertEquals(seasonTicket.getId(), foundTicket.get().getId());
    }

    @Test
    public void TestFindAll() {
        User user = User.builder()
                .id(2L)
                .firstName("Test")
                .lastName("Senla")
                .password("1234")
                .email("test2@test.com")
                .build();
        userRepository.save(user);

        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(2L)
                .name("Weekly")
                .price(BigDecimal.valueOf(123))
                .build();
        seasonTicketsTypeRepository.save(seasonTicketsType);

        SeasonTickets seasonTicket1 = SeasonTickets.builder()
                .id(2L)
                .startTime(Timestamp.valueOf("2024-09-01 00:00:00"))
                .endTime(Timestamp.valueOf("2024-09-07 00:00:00"))
                .user(user)
                .seasonTicketsType(seasonTicketsType)
                .build();

        SeasonTickets seasonTicket2 = SeasonTickets.builder()
                .id(3L)
                .startTime(Timestamp.valueOf("2024-09-08 00:00:00"))
                .endTime(Timestamp.valueOf("2024-09-14 00:00:00"))
                .user(user)
                .seasonTicketsType(seasonTicketsType)
                .build();

        seasonTicketsRepository.save(seasonTicket1);
        seasonTicketsRepository.save(seasonTicket2);

        List<SeasonTickets> seasonTickets = seasonTicketsRepository.findAll();

        assertEquals(2, seasonTickets.size());
    }

    @Test
    public void TestSave() {
        User user = User.builder()
                .id(3L)
                .firstName("Test")
                .lastName("Senla")
                .password("1234")
                .email("test3@test.com")
                .build();
        userRepository.save(user);

        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(3L)
                .name("Annual")
                .price(BigDecimal.valueOf(123))
                .build();
        seasonTicketsTypeRepository.save(seasonTicketsType);

        SeasonTickets seasonTicket = SeasonTickets.builder()
                .id(4L)
                .startTime(Timestamp.valueOf("2024-01-01 00:00:00"))
                .endTime(Timestamp.valueOf("2025-01-01 00:00:00"))
                .user(user)
                .seasonTicketsType(seasonTicketsType)
                .build();
        seasonTicketsRepository.save(seasonTicket);

        Optional<SeasonTickets> savedTicket = seasonTicketsRepository.findById(seasonTicket.getId());

        assertNotNull(savedTicket);
        assertEquals(seasonTicket.getStartTime(), savedTicket.get().getStartTime());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(4L)
                .firstName("Test")
                .password("1234")
                .lastName("Senla")
                .email("test4@test.com")
                .build();
        userRepository.save(user);

        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(4L)
                .name("Quarterly")
                .price(BigDecimal.valueOf(123))
                .build();
        seasonTicketsTypeRepository.save(seasonTicketsType);

        SeasonTickets seasonTicket = SeasonTickets.builder()
                .id(5L)
                .startTime(Timestamp.valueOf("2024-04-01 00:00:00"))
                .endTime(Timestamp.valueOf("2024-07-01 00:00:00"))
                .user(user)
                .seasonTicketsType(seasonTicketsType)
                .build();
        seasonTicketsRepository.save(seasonTicket);

        seasonTicketsRepository.deleteById(seasonTicket.getId());

        Optional<SeasonTickets> deletedTicket = seasonTicketsRepository.findById(seasonTicket.getId());

        assertTrue(deletedTicket.isEmpty());
    }
}
