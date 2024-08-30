package senla.com.repository;

import senla.com.entity.SeasonTickets;

import java.util.List;

public interface SeasonTicketsRepository {
    SeasonTickets findById(Long id);

    List<SeasonTickets> findAll();

    void save(SeasonTickets seasonTickets);

    void deleteById(Long id);
}
