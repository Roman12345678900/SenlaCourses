package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Payments;
import senla.com.entity.User;
import senla.com.repository.implementation.PaymentsRepositoryImpl;
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
public class PaymentsRepositoryTest {

    @Resource
    private PaymentsRepositoryImpl paymentsRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById() {
        Payments payment = Payments.builder()
                .id(1L)
                .paymentsDate(Timestamp.valueOf("2024-09-19 10:00:00"))
                .price(new BigDecimal("99.99"))
                .build();

        paymentsRepository.save(payment);

        Optional<Payments> foundPayment = paymentsRepository.findById(payment.getId());

        assertNotNull(foundPayment);
        assertEquals(payment.getId(), foundPayment.get().getId());
        assertEquals(payment.getPaymentsDate(), foundPayment.get().getPaymentsDate());
        assertEquals(payment.getPrice(), foundPayment.get().getPrice());
    }

    @Test
    public void TestFindAll() {
        User user1 = User.builder()
                .id(1L)
                .firstName("qwe")
                .lastName("asd")
                .email("qwe@asd.com")
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .id(2L)
                .firstName("qwe")
                .lastName("asd")
                .email("qwe@asd.com")
                .build();

        userRepository.save(user2);

        Payments payment1 = Payments.builder()
                .id(2L)
                .paymentsDate(Timestamp.valueOf("2024-09-20 11:00:00"))
                .price(BigDecimal.valueOf(123))
                .user(user1)
                .build();

        paymentsRepository.save(payment1);

        Payments payment2 = Payments.builder()
                .id(3L)
                .paymentsDate(Timestamp.valueOf("2024-09-21 12:00:00"))
                .price(BigDecimal.valueOf(123))
                .user(user2)
                .build();

        paymentsRepository.save(payment2);

        List<Payments> payments = paymentsRepository.findAll();

        assertEquals(2, payments.size());
    }

    @Test
    public void TestSave() {
        Payments payment = Payments.builder()
                .id(4L)
                .paymentsDate(Timestamp.valueOf("2024-09-22 13:00:00"))
                .price(new BigDecimal("79.99"))
                .build();

        paymentsRepository.save(payment);

        Optional<Payments> savedPayment = paymentsRepository.findById(payment.getId());

        assertNotNull(savedPayment);
        assertEquals(payment.getPaymentsDate(), savedPayment.get().getPaymentsDate());
        assertEquals(payment.getPrice(), savedPayment.get().getPrice());
    }

    @Test
    public void TestDelete() {
        Payments payment = Payments.builder()
                .id(5L)
                .paymentsDate(Timestamp.valueOf("2024-09-23 14:00:00"))
                .price(new BigDecimal("89.99"))
                .build();

        paymentsRepository.save(payment);

        paymentsRepository.deleteById(payment.getId());

        Optional<Payments> deletedPayment = paymentsRepository.findById(payment.getId());

        assertTrue(deletedPayment.isEmpty());
    }
}
