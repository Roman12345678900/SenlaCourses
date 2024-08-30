package senla.com.repository;

import senla.com.entity.Role;

import java.util.List;

public interface RoleRepository {
    Role findById(Long id);

    List<Role> findAll();

    void save(Role role);

    void deleteById(Long id);
}
