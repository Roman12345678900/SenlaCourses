package senla.com.repository;

import senla.com.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String username);

    List<User> findByNameWithApi(String name);

    List<User> findAllWithRoles();
}
