package senla.com.repository;

import senla.com.entity.Payments;

import java.util.List;

public interface PaymentsRepository {

    List<Payments> findAllWithJpql();

    List<Payments> findWithCriteriaApi();

    List<Payments> findWithEntityGraph();
}
