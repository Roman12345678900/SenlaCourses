package senla.com.repository;

import senla.com.entity.SeasonTickets;

import java.util.List;

public interface SeasonTicketsRepository {

    List<SeasonTickets> findAllWithFetchCriteria();

    List<SeasonTickets> findAllWithJpql();

    List<SeasonTickets> findAllWithEntityGraph();
}
