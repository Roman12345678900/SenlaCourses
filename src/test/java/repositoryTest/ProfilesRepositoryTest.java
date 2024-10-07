package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Profiles;
import senla.com.entity.User;
import senla.com.repository.implementation.ProfilesRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class ProfilesRepositoryTest {

    @Resource
    private ProfilesRepositoryImpl profilesRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById() {
        User user = User.builder()
                .id(1L)
                .firstName("Alice")
                .password("1234")
                .lastName("Johnson")
                .email("alice.johnson@test.com")
                .build();
        userRepository.save(user);

        Profiles profile = Profiles.builder()
                .id(1L)
                .address("123 Main St")
                .dateOfBirth(Date.valueOf("1990-01-01"))
                .gender("Female")
                .phone("555-1234")
                .user(user) // Связываем с пользователем
                .build();
        profilesRepository.save(profile);

        Optional<Profiles> foundProfile = profilesRepository.findById(profile.getId());

        assertNotNull(foundProfile);
        assertEquals(profile.getId(), foundProfile.get().getId());
    }

    @Test
    public void TestFindAll() {
        User user1 = User.builder()
                .id(2L)
                .firstName("Bob")
                .password("1234")
                .lastName("Smith")
                .email("bob.smith@test.com")
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .id(3L)
                .firstName("Charlie")
                .password("1234")
                .lastName("Brown")
                .email("charlie.brown@test.com")
                .build();
        userRepository.save(user2);

        Profiles profile1 = Profiles.builder()
                .id(2L)
                .address("456")
                .dateOfBirth(Date.valueOf("1985-05-15"))
                .gender("Male")
                .phone("555-5678")
                .user(user1)
                .build();

        Profiles profile2 = Profiles.builder()
                .id(3L)
                .address("789")
                .dateOfBirth(Date.valueOf("1992-08-20"))
                .gender("Non-binary")
                .phone("555-8765")
                .user(user2)
                .build();

        profilesRepository.save(profile1);
        profilesRepository.save(profile2);

        List<Profiles> profiles = profilesRepository.findAll();

        assertEquals(2, profiles.size());
    }

    @Test
    public void TestSave() {
        User user = User.builder()
                .id(4L)
                .firstName("David")
                .password("1234")
                .lastName("Williams")
                .email("david.williams@test.com")
                .build();
        userRepository.save(user);

        Profiles profile = Profiles.builder()
                .id(4L)
                .address("101 Pine St")
                .dateOfBirth(Date.valueOf("1978-12-31"))
                .gender("Male")
                .phone("555-4321")
                .user(user)
                .build();
        profilesRepository.save(profile);

        Optional<Profiles> savedProfile = profilesRepository.findById(profile.getId());

        assertNotNull(savedProfile);
        assertEquals(profile.getUser().getId(), savedProfile.get().getUser().getId());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(5L)
                .firstName("Eve")
                .lastName("Taylor")
                .password("1234")
                .email("eve.taylor@test.com")
                .build();
        userRepository.save(user);

        Profiles profile = Profiles.builder()
                .id(5L)
                .address("202 Maple St")
                .dateOfBirth(Date.valueOf("2000-01-01"))
                .gender("Female")
                .phone("555-0000")
                .user(user)
                .build();
        profilesRepository.save(profile);

        profilesRepository.deleteById(profile.getId());

        Optional<Profiles> deletedProfile = profilesRepository.findById(profile.getId());

        assertTrue(deletedProfile.isEmpty());
    }
}
