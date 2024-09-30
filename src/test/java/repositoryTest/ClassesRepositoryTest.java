package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Classes;
import senla.com.entity.User;
import senla.com.repository.implementation.ClassesRepositoryImpl;
import senla.com.repository.implementation.UserRepositoryImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class ClassesRepositoryTest {

    @Resource
    private ClassesRepositoryImpl classesRepository;

    @Resource
    private UserRepositoryImpl userRepository;

    @Test
    public void TestFindById() {
        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .build();
        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(1L)
                .name("Yoga")
                .description("Yoga class for all levels")
                .users(user)
                .build();
        classesRepository.save(classes);

        Optional<Classes> foundClass = classesRepository.findById(classes.getId());

        assertNotNull(foundClass);
        assertEquals(classes.getId(), foundClass.get().getId());
        assertEquals(classes.getName(), foundClass.get().getName());
        assertEquals(classes.getDescription(), foundClass.get().getDescription());
        assertEquals(classes.getUsers().getId(), foundClass.get().getUsers().getId());
    }

    @Test
    public void TestFindAll() {
        User user1 = User.builder()
                .id(2L)
                .firstName("Alice")
                .lastName("Smith")
                .email("alice.smith@test.com")
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .id(3L)
                .firstName("Bob")
                .lastName("Johnson")
                .email("bob.johnson@test.com")
                .build();
        userRepository.save(user2);

        Classes classes1 = Classes.builder()
                .id(2L)
                .name("Pilates")
                .description("Pilates for beginners")
                .users(user1)
                .build();
        classesRepository.save(classes1);

        Classes classes2 = Classes.builder()
                .id(3L)
                .name("Zumba")
                .description("High-energy dance class")
                .users(user2)
                .build();
        classesRepository.save(classes2);

        List<Classes> classesList = classesRepository.findAll();

        assertEquals(2, classesList.size());
    }

    @Test
    public void TestSave() {
        User user = User.builder()
                .id(4L)
                .firstName("Carol")
                .lastName("Williams")
                .email("carol.williams@test.com")
                .build();
        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(4L)
                .name("Spin")
                .description("Intense cycling class")
                .users(user)
                .build();
        classesRepository.save(classes);

        Optional<Classes> savedClass = classesRepository.findById(classes.getId());

        assertNotNull(savedClass);
        assertEquals(classes.getName(), savedClass.get().getName());
        assertEquals(classes.getDescription(), savedClass.get().getDescription());
        assertEquals(classes.getUsers().getId(), savedClass.get().getUsers().getId());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(6L)
                .firstName("Emma")
                .lastName("Jones")
                .email("emma.jones@test.com")
                .build();
        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(6L)
                .name("Circuit Training")
                .description("Full-body circuit training")
                .users(user)
                .build();
        classesRepository.save(classes);

        classesRepository.deleteById(classes.getId());

        Optional<Classes> deletedClass = classesRepository.findById(classes.getId());

        assertTrue(deletedClass.isEmpty());
    }
}
