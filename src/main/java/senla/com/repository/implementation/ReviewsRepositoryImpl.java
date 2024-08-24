package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Reviews;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ReviewsRepository;

@Repository
public class ReviewsRepositoryImpl extends AbstractRepository<Reviews> implements ReviewsRepository {
}
