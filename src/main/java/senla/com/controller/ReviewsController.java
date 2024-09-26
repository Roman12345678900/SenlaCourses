package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.ReviewsDto;
import senla.com.service.ReviewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @GetMapping("/{id}")
    public ReviewsDto findById(@PathVariable("id") Long id) {
        return reviewsService.findById(id);
    }

    @GetMapping
    public List<ReviewsDto> findAll() {
        return reviewsService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ReviewsDto reviewsDto) {
        reviewsService.save(reviewsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        reviewsService.deleteById(id);
    }
}
