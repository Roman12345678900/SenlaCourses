package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.ReviewsDto;
import senla.com.entity.Reviews;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ReviewsRepository;
import senla.com.service.ReviewsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final GenericMapper genericMapper;

    @Override
    public ReviewsDto findById(Long id) {
        return genericMapper.convertToDto(reviewsRepository.findById(id), ReviewsDto.class);
    }

    @Override
    public List<ReviewsDto> findAll() {
        return reviewsRepository.findAll().stream()
                .map(reviews -> genericMapper.convertToDto(reviews,ReviewsDto.class))
                .toList();
    }

    @Override
    public void save(ReviewsDto reviewsDto) {
        Reviews reviews = genericMapper.convertToEntity(reviewsDto, Reviews.class);
        reviewsRepository.save(reviews);
    }

    @Override
    public void deleteById(Long id) {
        reviewsRepository.deleteById(id);
    }
}
