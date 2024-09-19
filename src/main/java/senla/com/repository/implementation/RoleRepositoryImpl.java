package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Role;
import senla.com.entity.Role_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.RoleRepository;

import java.util.List;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, Long> implements RoleRepository {

    @Override
    public Class<Role> getEntityclass() {
        return Role.class;
    }

    @Override
    public List<Role> findByNameWithFetchCriteria(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);

        query.select(root).where(criteriaBuilder.equal(root.get(Role_.name), name));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<Role> findAllWithFetchJPQL() {
        String jpql = "select r from Role r";

        return entityManager.createQuery(jpql, Role.class).getResultList();
    }

    @Override
    public List<Role> findAllWithEntityGraph() {
        EntityGraph<Role> entityGraph = entityManager.createEntityGraph(Role.class);

        return entityManager.createQuery("select r from Role r", Role.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }
}
