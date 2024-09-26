package senla.com.repository;

import senla.com.entity.Classes;

import java.util.List;

public interface ClassesRepository extends GenericRepository<Classes, Long>{


    List<Classes> findAllWithUserCriteria();

    List<Classes> findAllWithUserJPQL();

    List<Classes> findAllWithUserEntityGraph();
}
