package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.ReviewsDto;
import senla.com.entity.Reviews;
import senla.com.exception.ReviewsNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.ReviewsRepository;
import senla.com.service.ReviewsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final GenericRepository<Reviews, Long> reviewsRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public ReviewsDto findById(Long id) {
        Reviews reviews = reviewsRepository.findById(id).
                orElseThrow(() -> new ReviewsNotFoundException(id));
        return genericMapper.convertToDto(reviews, ReviewsDto.class);
    }

    @Override
    @Transactional
    public List<ReviewsDto> findAll() {
        return reviewsRepository.findAll().stream()
                .map(reviews -> genericMapper.convertToDto(reviews,ReviewsDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(ReviewsDto reviewsDto) {
        Reviews reviews = genericMapper.convertToEntity(reviewsDto, Reviews.class);
        reviewsRepository.save(reviews);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        reviewsRepository.deleteById(id);
    }
}
