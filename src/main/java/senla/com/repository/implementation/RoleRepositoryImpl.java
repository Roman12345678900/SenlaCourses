package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Role;
import senla.com.repository.AbstractRepository;
import senla.com.repository.RoleRepository;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role> implements RoleRepository {
}
