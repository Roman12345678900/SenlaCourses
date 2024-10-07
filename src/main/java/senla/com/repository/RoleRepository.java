package senla.com.repository;

import senla.com.entity.Role;

import java.util.List;

public interface RoleRepository extends GenericRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAllWithFetchJPQL();

    List<Role> findAllWithEntityGraph();
}
