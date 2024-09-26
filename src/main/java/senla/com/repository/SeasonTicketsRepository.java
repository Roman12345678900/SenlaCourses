package senla.com.repository;

import senla.com.entity.SeasonTickets;

import java.util.List;

public interface SeasonTicketsRepository extends GenericRepository<SeasonTickets, Long> {

    List<SeasonTickets> findAllWithFetchCriteria();

    List<SeasonTickets> findAllWithJpql();

    List<SeasonTickets> findAllWithEntityGraph();
}
