package senla.com.repository;

import senla.com.entity.TrainerSchedules;

import java.util.List;

public interface TrainerSchedulesRepository {

    List<TrainerSchedules> findAllWithCriteria();

    List<TrainerSchedules> findAllWithJpql();

    List<TrainerSchedules> findAllWithGraph();
}
