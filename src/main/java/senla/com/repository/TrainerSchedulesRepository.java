package senla.com.repository;

import senla.com.entity.TrainerSchedules;

import java.util.List;

public interface TrainerSchedulesRepository {
    TrainerSchedules findById(Long id);

    List<TrainerSchedules> findAll();

    void save(TrainerSchedules trainerSchedules);

    void deleteById(Long id);
}
