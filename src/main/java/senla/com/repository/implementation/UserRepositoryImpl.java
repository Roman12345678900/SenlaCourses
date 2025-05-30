package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.User;
import senla.com.entity.User_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.UserRepository;

import java.util.List;


@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Long> implements UserRepository {

    @Override
    public Class<User> getEntityclass() {
        return User.class;
    }

    @Override
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }


    @Override
    public List<User> findByNameWithApi(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.equal(root.get(User_.firstName), name));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<User> findAllWithRoles() {
        EntityGraph<User> entityGraph = entityManager.createEntityGraph(User.class);
        entityGraph.addAttributeNodes("roles");

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        query.setHint("javax.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
