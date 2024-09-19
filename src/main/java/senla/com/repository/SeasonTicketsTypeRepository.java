package senla.com.repository;

import senla.com.entity.SeasonTicketsType;

import java.util.List;

public interface SeasonTicketsTypeRepository {

    List<SeasonTicketsType> findByNameWithCriteria(String name);

    List<SeasonTicketsType> findAllWithJpql();

    List<SeasonTicketsType> findAllWithGraph();
}
