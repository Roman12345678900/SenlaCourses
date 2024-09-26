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
import senla.com.dto.ReviewsDto;
import senla.com.entity.Classes;
import senla.com.entity.Reviews;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ReviewsRepository;
import senla.com.service.implementation.ReviewsServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class ReviewsServiceTest {

    @Mock
    private ReviewsRepository reviewsRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private ReviewsServiceImpl reviewsService;

    @Before
    public void setUp() {
        reviewsRepository = Mockito.mock(ReviewsRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        reviewsService = new ReviewsServiceImpl(reviewsRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Reviews review = new Reviews();
        review.setId(id);
        review.setRating(5);
        review.setReviewText("Excellent class!");
        review.setReviewDate(Date.valueOf("2024-09-25"));

        Classes classes = new Classes();
        review.setClasses(classes);

        User user = new User();
        review.setUser(user);

        when(reviewsRepository.findById(id)).thenReturn(Optional.of(review));

        ReviewsDto reviewDto = new ReviewsDto();
        reviewDto.setId(id);
        reviewDto.setRating(5);
        reviewDto.setReviewText("Excellent class!");
        reviewDto.setReviewDate(Date.valueOf("2024-09-25"));
        when(genericMapper.convertToDto(review, ReviewsDto.class)).thenReturn(reviewDto);

        ReviewsDto result = reviewsService.findById(id);

        verify(reviewsRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(review, ReviewsDto.class);

        assertEquals(id, result.getId());
        assertEquals(review.getRating(), result.getRating());
        assertEquals(review.getReviewText(), result.getReviewText());
        assertEquals(review.getReviewDate(), result.getReviewDate());
    }

    @Test
    public void testFindAll() {
        List<Reviews> reviewsList = new ArrayList<>();
        reviewsList.add(new Reviews(1L, 5, "Excellent class!", Date.valueOf("2024-09-25"), null, null));
        reviewsList.add(new Reviews(2L, 4, "Good class.", Date.valueOf("2024-09-26"), null, null));

        when(reviewsRepository.findAll()).thenReturn(reviewsList);
        List<ReviewsDto> result = reviewsService.findAll();

        assertEquals(reviewsList.size(), result.size());
        verify(reviewsRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Reviews review = new Reviews();
        review.setRating(5);
        review.setReviewText("Excellent class!");
        review.setReviewDate(Date.valueOf("2024-09-25"));

        Classes classes = new Classes();
        review.setClasses(classes);

        User user = new User();
        review.setUser(user);

        ReviewsDto reviewDto = new ReviewsDto();
        reviewDto.setRating(5);
        reviewDto.setReviewText("Excellent class!");
        reviewDto.setReviewDate(Date.valueOf("2024-09-25"));

        when(genericMapper.convertToEntity(reviewDto, Reviews.class)).thenReturn(review);

        reviewsService.save(reviewDto);

        verify(genericMapper, times(1)).convertToEntity(reviewDto, Reviews.class);
        verify(reviewsRepository, times(1)).save(review);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        reviewsService.deleteById(id);
        verify(reviewsRepository, times(1)).deleteById(id);
    }
}
