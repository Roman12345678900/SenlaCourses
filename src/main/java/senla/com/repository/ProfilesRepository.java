package senla.com.repository;

import senla.com.entity.Profiles;

import java.util.List;

public interface ProfilesRepository {

    List<Profiles> findWithJpql();

    List<Profiles> findWithCriteriaApi();

    List<Profiles> findWithEntityGraph();
}
