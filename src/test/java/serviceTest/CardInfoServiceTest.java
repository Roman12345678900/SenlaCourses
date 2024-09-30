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
import senla.com.dto.CardInfoDto;
import senla.com.entity.CardInfo;
import senla.com.entity.TrainerSchedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.CardInfoRepository;
import senla.com.service.implementation.CardInfoServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class CardInfoServiceTest {

    @Mock
    private CardInfoRepository cardInfoRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private CardInfoServiceImpl cardInfoService;

    @Before
    public void setUp() {
        cardInfoRepository = Mockito.mock(CardInfoRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        cardInfoService = new CardInfoServiceImpl(cardInfoRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long cardNumber = 1234567890123456L;
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(cardNumber);
        cardInfo.setCardHolderName("John Doe");
        cardInfo.setValidityPeriodStart(LocalDate.of(2022, 1, 1));
        cardInfo.setValidityPeriodEnd(LocalDate.of(2025, 1, 1));

        when(cardInfoRepository.findById(cardNumber)).thenReturn(Optional.of(cardInfo));

        CardInfoDto cardInfoDto = new CardInfoDto();
        cardInfoDto.setCardNumber(cardNumber);
        cardInfoDto.setCardHolderName("John Doe");
        cardInfoDto.setValidityPeriodStart(LocalDate.of(2022, 1, 1));
        cardInfoDto.setValidityPeriodEnd(LocalDate.of(2025, 1, 1));

        when(genericMapper.convertToDto(cardInfo, CardInfoDto.class)).thenReturn(cardInfoDto);

        CardInfoDto result = cardInfoService.findById(cardNumber);

        verify(cardInfoRepository, times(1)).findById(cardNumber);
        verify(genericMapper, times(1)).convertToDto(cardInfo, CardInfoDto.class);

        assertEquals(cardNumber, result.getCardNumber());
        assertEquals("John Doe", result.getCardHolderName());
        assertEquals(LocalDate.of(2022, 1, 1), result.getValidityPeriodStart());
        assertEquals(LocalDate.of(2025, 1, 1), result.getValidityPeriodEnd());
    }

    @Test
    public void testFindAll() {
        List<CardInfo> cardInfoList = new ArrayList<>();
        cardInfoList.add(new CardInfo(1234567890123456L, "John Doe", LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1), null));
        cardInfoList.add(new CardInfo(2345678901234567L, "Jane Smith", LocalDate.of(2021, 5, 1), LocalDate.of(2024, 5, 1), null));

        when(cardInfoRepository.findAll()).thenReturn(cardInfoList);
        List<CardInfoDto> result = cardInfoService.findAll();

        assertEquals(cardInfoList.size(), result.size());
        verify(cardInfoRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(1234567890123456L);
        cardInfo.setCardHolderName("John Doe");
        cardInfo.setValidityPeriodStart(LocalDate.of(2022, 1, 1));
        cardInfo.setValidityPeriodEnd(LocalDate.of(2025, 1, 1));

        CardInfoDto cardInfoDto = new CardInfoDto();
        cardInfoDto.setCardNumber(1234567890123456L);
        cardInfoDto.setCardHolderName("John Doe");
        cardInfoDto.setValidityPeriodStart(LocalDate.of(2022, 1, 1));
        cardInfoDto.setValidityPeriodEnd(LocalDate.of(2025, 1, 1));

        when(genericMapper.convertToEntity(cardInfoDto, CardInfo.class)).thenReturn(cardInfo);

        cardInfoService.save(cardInfoDto);

        verify(genericMapper, times(1)).convertToEntity(cardInfoDto, CardInfo.class);
        verify(cardInfoRepository, times(1)).save(cardInfo);
    }

    @Test
    public void testDeleteById() {
        Long cardNumber = 1234567890123456L;
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(cardNumber);

        when(cardInfoRepository.findById(cardNumber)).thenReturn(Optional.of(cardInfo));

        cardInfoService.deleteById(cardNumber);

        verify(cardInfoRepository, times(1)).deleteById(cardNumber);
    }
}
