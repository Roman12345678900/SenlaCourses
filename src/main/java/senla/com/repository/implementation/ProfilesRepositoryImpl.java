package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Profiles;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ProfilesRepository;

@Repository
public class ProfilesRepositoryImpl extends AbstractRepository<Profiles> implements ProfilesRepository {
}
