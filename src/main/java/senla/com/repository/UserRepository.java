package senla.com.repository;

import senla.com.entity.User;

import java.util.List;

public interface UserRepository {
    User findById(Long id);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);
}
