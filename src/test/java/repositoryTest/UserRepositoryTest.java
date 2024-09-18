package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.User;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ApplicationConfiguration.class,
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class UserRepositoryTest {

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById(){
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        userRepository.save(user);

        User found = userRepository.findById(user.getId());

        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }

    @Test
    public void TestFindAll(){
        User user1 = User.builder()
                .id(5L)
                .firstName("Test5")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        User user2 = User.builder()
                .id(11L)
                .firstName("Test11")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertEquals(2, users.size());
    }

    @Test
    public void TestSave(){
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        userRepository.save(user);

        User testUser = userRepository.findById(user.getId());

        assertNotNull(testUser);
        assertEquals(user.getFirstName(), testUser.getFirstName());
    }

    @Test
    public void TestDelete(){
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Senla")
                .email("test@test.com")
                .build();

        userRepository.save(user);

        userRepository.deleteById(user.getId());

        User deletedUser = userRepository.findById(user.getId());

        assertNull(deletedUser);
    }
}
