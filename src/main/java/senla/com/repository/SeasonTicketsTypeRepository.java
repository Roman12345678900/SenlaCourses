package senla.com.repository;

import senla.com.entity.SeasonTicketsType;

import java.util.List;

public interface SeasonTicketsTypeRepository {
    SeasonTicketsType findById(Long id);

    List<SeasonTicketsType> findAll();

    void save(SeasonTicketsType seasonTicketsType);

    void deleteById(Long id);
}
