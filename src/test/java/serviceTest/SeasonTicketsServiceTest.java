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
import senla.com.dto.SeasonTicketsDto;
import senla.com.entity.SeasonTickets;
import senla.com.entity.SeasonTicketsType;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.SeasonTicketsRepository;
import senla.com.service.implementation.SeasonTicketsServiceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class SeasonTicketsServiceTest {

    @Mock
    private SeasonTicketsRepository seasonTicketsRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private SeasonTicketsServiceImpl seasonTicketsService;

    @Before
    public void setUp() {
        seasonTicketsRepository = Mockito.mock(SeasonTicketsRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        seasonTicketsService = new SeasonTicketsServiceImpl(seasonTicketsRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        SeasonTickets seasonTicket = new SeasonTickets();
        seasonTicket.setId(id);
        seasonTicket.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));
        seasonTicket.setEndTime(Timestamp.valueOf("2024-09-30 10:00:00"));

        SeasonTicketsType seasonTicketsType = new SeasonTicketsType(1L, "Standard", BigDecimal.valueOf(99.99));
        seasonTicket.setSeasonTicketsType(seasonTicketsType);

        User user = new User();
        seasonTicket.setUser(user);

        when(seasonTicketsRepository.findById(id)).thenReturn(Optional.of(seasonTicket));

        SeasonTicketsDto seasonTicketDto = new SeasonTicketsDto();
        seasonTicketDto.setId(id);
        seasonTicketDto.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));
        seasonTicketDto.setEndTime(Timestamp.valueOf("2024-09-30 10:00:00"));
        when(genericMapper.convertToDto(seasonTicket, SeasonTicketsDto.class)).thenReturn(seasonTicketDto);

        SeasonTicketsDto result = seasonTicketsService.findById(id);

        verify(seasonTicketsRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(seasonTicket, SeasonTicketsDto.class);

        assertEquals(id, result.getId());
        assertEquals(seasonTicket.getStartTime(), result.getStartTime());
        assertEquals(seasonTicket.getEndTime(), result.getEndTime());
    }

    @Test
    public void testFindAll() {
        List<SeasonTickets> seasonTickets = new ArrayList<>();
        seasonTickets.add(new SeasonTickets(1L, Timestamp.valueOf("2024-09-25 10:00:00"),
                Timestamp.valueOf("2024-09-30 10:00:00"), null, null));
        seasonTickets.add(new SeasonTickets(2L, Timestamp.valueOf("2024-10-01 10:00:00"),
                Timestamp.valueOf("2024-10-10 10:00:00"), null, null));

        when(seasonTicketsRepository.findAll()).thenReturn(seasonTickets);
        List<SeasonTicketsDto> result = seasonTicketsService.findAll();

        assertEquals(seasonTickets.size(), result.size());
        verify(seasonTicketsRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        SeasonTickets seasonTicket = new SeasonTickets();
        seasonTicket.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));
        seasonTicket.setEndTime(Timestamp.valueOf("2024-09-30 10:00:00"));

        SeasonTicketsType seasonTicketsType = new SeasonTicketsType(1L, "Standard", BigDecimal.valueOf(99.99));
        seasonTicket.setSeasonTicketsType(seasonTicketsType);

        SeasonTicketsDto seasonTicketDto = new SeasonTicketsDto();
        seasonTicketDto.setStartTime(Timestamp.valueOf("2024-09-25 10:00:00"));
        seasonTicketDto.setEndTime(Timestamp.valueOf("2024-09-30 10:00:00"));

        when(genericMapper.convertToEntity(seasonTicketDto, SeasonTickets.class)).thenReturn(seasonTicket);

        seasonTicketsService.save(seasonTicketDto);

        verify(genericMapper, times(1)).convertToEntity(seasonTicketDto, SeasonTickets.class);
        verify(seasonTicketsRepository, times(1)).save(seasonTicket);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        seasonTicketsService.deleteById(id);
        verify(seasonTicketsRepository, times(1)).deleteById(id);
    }
}
