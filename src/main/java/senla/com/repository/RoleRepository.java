package senla.com.repository;

import senla.com.entity.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> findAllWithFetchCriteria();

    List<Role> findAllWithFetchJPQL();

    List<Role> findAllWithEntityGraph();
}
