package senla.com.repository;

import senla.com.entity.Reviews;

import java.util.List;

public interface ReviewsRepository {

    List<Reviews> findWithJpql();

    List<Reviews> findAllWithFetch();

    List<Reviews> findAllWithEntityGraph();

    Reviews findByIdWithAssociationsNamedEntityGraph(Long id);
}
