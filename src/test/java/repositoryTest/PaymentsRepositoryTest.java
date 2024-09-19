package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ApplicationConfiguration.class,
        loader = AnnotationConfigContextLoader.class
)
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

        Payments foundPayment = paymentsRepository.findById(payment.getId());

        assertNotNull(foundPayment);
        assertEquals(payment.getId(), foundPayment.getId());
        assertEquals(payment.getPaymentsDate(), foundPayment.getPaymentsDate());
        assertEquals(payment.getPrice(), foundPayment.getPrice());
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

        Payments savedPayment = paymentsRepository.findById(payment.getId());

        assertNotNull(savedPayment);
        assertEquals(payment.getPaymentsDate(), savedPayment.getPaymentsDate());
        assertEquals(payment.getPrice(), savedPayment.getPrice());
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

        Payments deletedPayment = paymentsRepository.findById(payment.getId());

        assertNull(deletedPayment);
    }
}
