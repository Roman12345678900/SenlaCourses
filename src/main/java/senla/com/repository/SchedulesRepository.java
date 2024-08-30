package senla.com.repository;

import senla.com.entity.Schedules;

import java.util.List;

public interface SchedulesRepository {
    Schedules findById(Long id);

    List<Schedules> findAll();

    void save(Schedules schedules);

    void deleteById(Long id);
}
