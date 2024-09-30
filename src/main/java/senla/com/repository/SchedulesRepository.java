package senla.com.repository;


import senla.com.entity.Schedules;

import java.util.List;

public interface SchedulesRepository extends GenericRepository<Schedules, Long> {

    List<Schedules> findAllWithFetchCriteria();

    Schedules findByIdWithFetch(Long id);

    List<Schedules> findAllWithEntityGraph();
}
