package senla.com.repository;

import senla.com.entity.Classes;

import java.util.List;

public interface ClassesRepository {


    List<Classes> findAllWithUserCriteria();

    List<Classes> findAllWithUserJPQL();

    List<Classes> findAllWithUserEntityGraph();
}
