package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Classes;
import senla.com.entity.Classes_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ClassesRepository;

import java.util.List;

@Repository
public class ClassesRepositoryImpl extends AbstractRepository<Classes, Long> implements ClassesRepository {

    @Override
    public Class<Classes> getEntityclass() {
        return Classes.class;
    }

    @Override
    public List<Classes> findAllWithUserCriteria() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Classes> cq = cb.createQuery(Classes.class);
        Root<Classes> classesRoot = cq.from(Classes.class);
        classesRoot.fetch(Classes_.users, JoinType.LEFT);
        cq.select(classesRoot);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Classes> findAllWithUserJPQL() {
        String jpql = "select c from Classes c left join fetch c.users";
        TypedQuery<Classes> query = entityManager.createQuery(jpql, Classes.class);

        return query.getResultList();
    }

    @Override
    public List<Classes> findAllWithUserEntityGraph() {
        EntityGraph<Classes> entityGraph = entityManager.createEntityGraph(Classes.class);
        return entityManager.createQuery("select c from Classes c", Classes.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
    }
}
