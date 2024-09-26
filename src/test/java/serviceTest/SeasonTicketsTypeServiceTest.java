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
import senla.com.dto.SeasonTicketsTypeDto;
import senla.com.entity.SeasonTicketsType;
import senla.com.mapper.GenericMapper;
import senla.com.repository.SeasonTicketsTypeRepository;
import senla.com.service.implementation.SeasonTicketsTypeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class SeasonTicketsTypeServiceTest {

    @Mock
    private SeasonTicketsTypeRepository seasonTicketsTypeRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private SeasonTicketsTypeServiceImpl seasonTicketsTypeService;

    @Before
    public void setUp() {
        seasonTicketsTypeRepository = Mockito.mock(SeasonTicketsTypeRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        seasonTicketsTypeService = new SeasonTicketsTypeServiceImpl(seasonTicketsTypeRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        SeasonTicketsType seasonTicketType = new SeasonTicketsType();
        seasonTicketType.setId(id);
        seasonTicketType.setName("Standard");
        seasonTicketType.setPrice(BigDecimal.valueOf(99.99));

        when(seasonTicketsTypeRepository.findById(id)).thenReturn(Optional.of(seasonTicketType));

        SeasonTicketsTypeDto seasonTicketTypeDto = new SeasonTicketsTypeDto();
        seasonTicketTypeDto.setId(id);
        seasonTicketTypeDto.setName("Standard");
        when(genericMapper.convertToDto(seasonTicketType, SeasonTicketsTypeDto.class)).thenReturn(seasonTicketTypeDto);

        SeasonTicketsTypeDto result = seasonTicketsTypeService.findById(id);

        verify(seasonTicketsTypeRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(seasonTicketType, SeasonTicketsTypeDto.class);

        assertEquals(id, result.getId());
        assertEquals("Standard", result.getName());
    }

    @Test
    public void testFindAll() {
        List<SeasonTicketsType> seasonTicketTypes = new ArrayList<>();
        seasonTicketTypes.add(new SeasonTicketsType(1L, "Standard", BigDecimal.valueOf(99.99)));
        seasonTicketTypes.add(new SeasonTicketsType(2L, "Premium", BigDecimal.valueOf(149.99)));

        when(seasonTicketsTypeRepository.findAll()).thenReturn(seasonTicketTypes);
        List<SeasonTicketsTypeDto> result = seasonTicketsTypeService.findAll();

        assertEquals(seasonTicketTypes.size(), result.size());
        verify(seasonTicketsTypeRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        SeasonTicketsType seasonTicketType = new SeasonTicketsType();
        seasonTicketType.setName("Standard");
        seasonTicketType.setPrice(BigDecimal.valueOf(99.99));

        SeasonTicketsTypeDto seasonTicketTypeDto = new SeasonTicketsTypeDto();
        seasonTicketTypeDto.setName("Standard");

        when(genericMapper.convertToEntity(seasonTicketTypeDto, SeasonTicketsType.class)).thenReturn(seasonTicketType);

        seasonTicketsTypeService.save(seasonTicketTypeDto);

        verify(genericMapper, times(1)).convertToEntity(seasonTicketTypeDto, SeasonTicketsType.class);
        verify(seasonTicketsTypeRepository, times(1)).save(seasonTicketType);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        seasonTicketsTypeService.deleteById(id);
        verify(seasonTicketsTypeRepository, times(1)).deleteById(id);
    }
}
