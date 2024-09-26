package senla.com.repository;

import senla.com.entity.Payments;

import java.util.List;

public interface PaymentsRepository extends GenericRepository<Payments, Long> {

    List<Payments> findAllWithJpql();

    List<Payments> findWithCriteriaApi();

    List<Payments> findWithEntityGraph();
}
