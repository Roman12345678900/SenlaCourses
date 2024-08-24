package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.ReviewsDto;

import java.util.List;

@Component
public interface ReviewsService {
    ReviewsDto findById(Long id);

    List<ReviewsDto> findAll();

    void save(ReviewsDto reviewsDto);

    void deleteById(Long id);
}
