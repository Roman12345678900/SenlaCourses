package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.SeasonTickets;
import senla.com.repository.AbstractRepository;
import senla.com.repository.SeasonTicketsRepository;

@Repository
public class SeasonTicketsRepositoryImpl extends AbstractRepository<SeasonTickets> implements SeasonTicketsRepository {
}
