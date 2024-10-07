package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.CardInfo;
import senla.com.entity.User;
import senla.com.repository.implementation.CardInfoRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class CardInfoRepositoryTest {

    @Resource
    private CardInfoRepositoryImpl cardInfoRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById() {
        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .password("1234")
                .email("john.doe@test.com")
                .build();
        userRepository.save(user);

        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(1234567890123456L)
                .cardHolderName("John Doe")
                .validityPeriodStart(LocalDate.of(2024, 1, 1))
                .validityPeriodEnd(LocalDate.of(2027, 1, 1))
                .user(user)
                .build();
        cardInfoRepository.save(cardInfo);

        Optional<CardInfo> foundCardInfo = cardInfoRepository.findById(cardInfo.getCardNumber());

        assertNotNull(foundCardInfo);
        assertEquals(cardInfo.getCardNumber(), foundCardInfo.get().getCardNumber());
    }

    @Test
    public void TestFindAll() {
        User user1 = User.builder()
                .id(2L)
                .firstName("Alice")
                .lastName("Smith")
                .email("alice.smith@test.com")
                .password("1234")
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .id(3L)
                .firstName("Bob")
                .password("1234")
                .lastName("Johnson")
                .email("bob.johnson@test.com")
                .build();

        userRepository.save(user2);

        CardInfo cardInfo1 = CardInfo.builder()
                .cardNumber(2345678901234567L)
                .cardHolderName("Alice Smith")
                .validityPeriodStart(LocalDate.of(2023, 6, 1))
                .validityPeriodEnd(LocalDate.of(2026, 6, 1))
                .user(user1)
                .build();

        cardInfoRepository.save(cardInfo1);

        CardInfo cardInfo2 = CardInfo.builder()
                .cardNumber(3456789012345678L)
                .cardHolderName("Bob Johnson")
                .validityPeriodStart(LocalDate.of(2024, 7, 1))
                .validityPeriodEnd(LocalDate.of(2027, 7, 1))
                .user(user2)
                .build();

        cardInfoRepository.save(cardInfo2);

        List<CardInfo> cardInfos = cardInfoRepository.findAll();

        assertEquals(2, cardInfos.size());
    }

    @Test
    public void TestSave() {
        User user = User.builder()
                .id(4L)
                .firstName("Carol")
                .password("1234")
                .lastName("Williams")
                .email("carol.williams@test.com")
                .build();

        userRepository.save(user);

        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(4567890123456789L)
                .cardHolderName("Carol Williams")
                .validityPeriodStart(LocalDate.of(2025, 8, 1))
                .validityPeriodEnd(LocalDate.of(2028, 8, 1))
                .user(user)
                .build();

        cardInfoRepository.save(cardInfo);

        Optional<CardInfo> savedCardInfo = cardInfoRepository.findById(cardInfo.getCardNumber());

        assertNotNull(savedCardInfo);
        assertEquals(cardInfo.getCardNumber(), savedCardInfo.get().getCardNumber());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(6L)
                .firstName("Emma")
                .password("1234")
                .lastName("Jones")
                .email("emma.jones@test.com")
                .build();

        userRepository.save(user);

        CardInfo cardInfo = CardInfo.builder()
                .cardNumber(6789012345678901L)
                .cardHolderName("Emma Jones")
                .validityPeriodStart(LocalDate.of(2023, 10, 1))
                .validityPeriodEnd(LocalDate.of(2026, 10, 1))
                .user(user)
                .build();

        cardInfoRepository.save(cardInfo);

        cardInfoRepository.deleteById(cardInfo.getCardNumber());

        Optional<CardInfo> deletedCardInfo = cardInfoRepository.findById(cardInfo.getCardNumber());

        assertTrue(deletedCardInfo.isEmpty());
    }

}
