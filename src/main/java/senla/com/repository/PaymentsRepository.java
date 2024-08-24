package senla.com.repository;

import senla.com.entity.Payments;

import java.util.List;

public interface PaymentsRepository {

    Payments findById(Long id);

    List<Payments> findAll();

    void save(Payments payments);

    void deleteById(Long id);
}
