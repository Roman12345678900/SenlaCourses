package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.User;
import senla.com.repository.AbstractRepository;
import senla.com.repository.UserRepository;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
}
