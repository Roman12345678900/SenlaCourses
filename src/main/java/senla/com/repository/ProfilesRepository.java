package senla.com.repository;

import senla.com.entity.Profiles;

import java.util.List;

public interface ProfilesRepository extends GenericRepository<Profiles, Long> {

    List<Profiles> findWithJpql();

    List<Profiles> findWithCriteriaApi();

    List<Profiles> findWithEntityGraph();
}
