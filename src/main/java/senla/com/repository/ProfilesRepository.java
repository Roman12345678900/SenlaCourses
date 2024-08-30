package senla.com.repository;

import senla.com.entity.Profiles;

import java.util.List;

public interface ProfilesRepository {
    Profiles findById(Long id);

    List<Profiles> findAll();

    void save(Profiles profiles);

    void deleteById(Long id);
}
