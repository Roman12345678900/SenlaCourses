package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.ReviewsDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.ReviewsService;

@Controller
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(reviewsService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(reviewsService.findAll());
    }

    public void save(String reviewsDto) {
        reviewsService.save(jsonMapper.deserialize(reviewsDto, ReviewsDto.class));
    }

    public void deleteById(Long id) {
        reviewsService.deleteById(id);
    }
}
