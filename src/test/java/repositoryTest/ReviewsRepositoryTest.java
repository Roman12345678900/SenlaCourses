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
import senla.com.entity.Reviews;
import senla.com.entity.User;
import senla.com.repository.implementation.ClassesRepositoryImpl;
import senla.com.repository.implementation.ReviewsRepositoryImpl;
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
public class ReviewsRepositoryTest {

    @Resource
    private ReviewsRepositoryImpl reviewsRepository;

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

        Reviews review = Reviews.builder()
                .id(1L)
                .rating(5)
                .reviewText("Excellent class!")
                .reviewDate(Date.valueOf("2024-09-18"))
                .classes(classes)
                .build();

        reviewsRepository.save(review);

        Optional<Reviews> foundReview = reviewsRepository.findById(review.getId());

        assertNotNull(foundReview);
        assertEquals(review.getId(), foundReview.get().getId());
        assertEquals(review.getRating(), foundReview.get().getRating());
    }

    @Test
    public void TestFindAll() {
        User user1 = User.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@test.com")
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .id(3L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@test.com")
                .build();

        userRepository.save(user2);

        Classes classes1 = Classes.builder()
                .id(2L)
                .name("Pilates")
                .description("Pilates class for beginners")
                .users(user1)
                .build();

        classesRepository.save(classes1);

        Classes classes2 = Classes.builder()
                .id(3L)
                .name("Boxing")
                .description("Boxing class for advanced")
                .users(user2)
                .build();

        classesRepository.save(classes2);

        Reviews review1 = Reviews.builder()
                .id(2L)
                .rating(4)
                .reviewText("Great class!")
                .reviewDate(Date.valueOf("2024-09-19"))
                .classes(classes1)
                .user(user1)
                .build();

        Reviews review2 = Reviews.builder()
                .id(3L)
                .rating(3)
                .reviewText("Good, but could be better.")
                .reviewDate(Date.valueOf("2024-09-20"))
                .classes(classes2)
                .user(user2)
                .build();

        reviewsRepository.save(review1);
        reviewsRepository.save(review2);

        List<Reviews> reviews = reviewsRepository.findAll();

        assertEquals(2, reviews.size());
    }


    @Test
    public void TestSave() {
        User user = User.builder()
                .id(3L)
                .firstName("Alice")
                .lastName("Smith")
                .email("alice.smith@test.com")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(3L)
                .name("Boxing")
                .description("Boxing class for advanced students")
                .users(user)
                .build();

        classesRepository.save(classes);

        Reviews review = Reviews.builder()
                .id(4L)
                .rating(5)
                .reviewText("Challenging and fun!")
                .reviewDate(Date.valueOf("2024-10-01"))
                .classes(classes)
                .build();

        reviewsRepository.save(review);

        Optional<Reviews> savedReview = reviewsRepository.findById(review.getId());

        assertNotNull(savedReview);
        assertEquals(review.getRating(), savedReview.get().getRating());
    }

    @Test
    public void TestDelete() {
        User user = User.builder()
                .id(4L)
                .firstName("Bob")
                .lastName("Johnson")
                .email("bob.johnson@test.com")
                .build();

        userRepository.save(user);

        Classes classes = Classes.builder()
                .id(4L)
                .name("Swimming")
                .description("Swimming class for all levels")
                .users(user)
                .build();

        classesRepository.save(classes);

        Reviews review = Reviews.builder()
                .id(5L)
                .rating(2)
                .reviewText("Not what I expected.")
                .reviewDate(Date.valueOf("2024-10-02"))
                .classes(classes)
                .build();

        reviewsRepository.save(review);

        reviewsRepository.deleteById(review.getId());

        Optional<Reviews> deletedReview = reviewsRepository.findById(review.getId());

        assertTrue(deletedReview.isEmpty());
    }
}
