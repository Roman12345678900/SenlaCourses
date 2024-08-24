package senla.com.repository;

import senla.com.entity.Reviews;

import java.util.List;

public interface ReviewsRepository {
    Reviews findById(Long id);

    List<Reviews> findAll();

    void save(Reviews reviews);

    void deleteById(Long id);

}
