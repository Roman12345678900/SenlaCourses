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
import senla.com.dto.PaymentsDto;
import senla.com.entity.Payments;
import senla.com.entity.TrainerSchedules;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.PaymentsRepository;
import senla.com.service.implementation.PaymentsServiceImpl;

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
public class PaymentsServiceTest {

    @Mock
    private PaymentsRepository paymentsRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    @Before
    public void setUp() {
        paymentsRepository = Mockito.mock(PaymentsRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        paymentsService = new PaymentsServiceImpl(paymentsRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Payments payment = new Payments();
        payment.setId(id);
        payment.setPaymentsDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        payment.setPrice(new BigDecimal("99.99"));

        User user = new User();
        payment.setUser(user);

        when(paymentsRepository.findById(id)).thenReturn(Optional.of(payment));

        PaymentsDto paymentDto = new PaymentsDto();
        paymentDto.setId(id);
        paymentDto.setPaymentsDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        when(genericMapper.convertToDto(payment, PaymentsDto.class)).thenReturn(paymentDto);

        PaymentsDto result = paymentsService.findById(id);

        verify(paymentsRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(payment, PaymentsDto.class);

        assertEquals(id, result.getId());
        assertEquals(payment.getPaymentsDate(), result.getPaymentsDate());
    }

    @Test
    public void testFindAll() {
        List<Payments> paymentsList = new ArrayList<>();
        paymentsList.add(new Payments(1L, Timestamp.valueOf("2024-01-01 10:00:00"), new BigDecimal("99.99"), null));
        paymentsList.add(new Payments(2L, Timestamp.valueOf("2024-01-02 11:00:00"), new BigDecimal("49.99"), null));

        when(paymentsRepository.findAll()).thenReturn(paymentsList);
        List<PaymentsDto> result = paymentsService.findAll();

        assertEquals(paymentsList.size(), result.size());
        verify(paymentsRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Payments payment = new Payments();
        payment.setPaymentsDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        payment.setPrice(new BigDecimal("99.99"));

        User user = new User();
        payment.setUser(user);

        PaymentsDto paymentDto = new PaymentsDto();
        paymentDto.setPaymentsDate(Timestamp.valueOf("2024-01-01 10:00:00"));

        when(genericMapper.convertToEntity(paymentDto, Payments.class)).thenReturn(payment);

        paymentsService.save(paymentDto);

        verify(genericMapper, times(1)).convertToEntity(paymentDto, Payments.class);
        verify(paymentsRepository, times(1)).save(payment);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        Payments payments = new Payments();
        payments.setId(id);

        when(paymentsRepository.findById(id)).thenReturn(Optional.of(payments));

        paymentsService.deleteById(id);

        verify(paymentsRepository, times(1)).deleteById(id);
    }
}
