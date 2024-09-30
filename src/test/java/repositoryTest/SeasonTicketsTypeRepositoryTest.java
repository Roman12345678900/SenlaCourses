package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.SeasonTicketsType;
import senla.com.repository.implementation.SeasonTicketsTypeRepositoryImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class SeasonTicketsTypeRepositoryTest {

    @Resource
    SeasonTicketsTypeRepositoryImpl seasonTicketsTypeRepository;

    @Test
    public void TestFindBiId() {
        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(1L)
                .name("Test Season Tickets Type")
                .price(BigDecimal.valueOf(1234))
                .build();

        seasonTicketsTypeRepository.save(seasonTicketsType);

        Optional<SeasonTicketsType> found = seasonTicketsTypeRepository.findById(seasonTicketsType.getId());

        assertNotNull(found);
        assertEquals(seasonTicketsType.getId(), found.get().getId());
    }

    @Test
    public void TestFindAll() {
        SeasonTicketsType seasonTicketsType1 = SeasonTicketsType.builder()
                .id(1L)
                .name("Test Season Tickets Type1")
                .price(BigDecimal.valueOf(1234))
                .build();

        SeasonTicketsType seasonTicketsType2 = SeasonTicketsType.builder()
                .id(12L)
                .name("Test Season Tickets Type2")
                .price(BigDecimal.valueOf(1234))
                .build();

        seasonTicketsTypeRepository.save(seasonTicketsType1);
        seasonTicketsTypeRepository.save(seasonTicketsType2);

        List<SeasonTicketsType> seasonTicketsTypes = seasonTicketsTypeRepository.findAll();

        assertEquals(2, seasonTicketsTypes.size());
    }

    @Test
    public void TestSave(){
        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(1L)
                .name("Test Season Tickets Type")
                .price(BigDecimal.valueOf(1234))
                .build();

        seasonTicketsTypeRepository.save(seasonTicketsType);

        Optional<SeasonTicketsType> seasonTicketsType1 = seasonTicketsTypeRepository.findById(seasonTicketsType.getId());

        assertNotNull(seasonTicketsType1);
        assertEquals(seasonTicketsType.getName(), seasonTicketsType1.get().getName());
    }

    @Test
    public void TestDelete(){
        SeasonTicketsType seasonTicketsType = SeasonTicketsType.builder()
                .id(1L)
                .name("Test Season Tickets Type")
                .price(BigDecimal.valueOf(1234))
                .build();

        seasonTicketsTypeRepository.save(seasonTicketsType);

        seasonTicketsTypeRepository.deleteById(seasonTicketsType.getId());

        Optional<SeasonTicketsType> seasonTicketsType1 = seasonTicketsTypeRepository.findById(seasonTicketsType.getId());

        assertTrue(seasonTicketsType1.isEmpty());
    }
}
