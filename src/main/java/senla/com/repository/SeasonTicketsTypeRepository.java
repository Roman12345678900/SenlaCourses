package senla.com.repository;

import senla.com.entity.SeasonTicketsType;

import java.util.List;

public interface SeasonTicketsTypeRepository {

    List<SeasonTicketsType> findAllWithCriteria();

    List<SeasonTicketsType> findAllWithJpql();

    List<SeasonTicketsType> findAllWithGraph();
}
