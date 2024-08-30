package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Payments;
import senla.com.repository.AbstractRepository;
import senla.com.repository.PaymentsRepository;

@Repository
public class PaymentsRepositoryImpl extends AbstractRepository<Payments> implements PaymentsRepository {
}
