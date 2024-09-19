package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Schedules;
import senla.com.entity.Schedules_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.SchedulesRepository;

import java.util.List;

@Repository
public class SchedulesRepositoryImpl extends AbstractRepository<Schedules, Long> implements SchedulesRepository {

    @Override
    public Class<Schedules> getEntityclass() {
        return Schedules.class;
    }

    @Override
    public List<Schedules> findAllWithFetchCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Schedules> criteriaQuery = criteriaBuilder.createQuery(Schedules.class);
        Root<Schedules> root = criteriaQuery.from(Schedules.class);

        root.fetch(Schedules_.classes);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Schedules findByIdWithFetch(Long id) {
        return entityManager.createQuery(
                        "select s from Schedules s left join fetch s.classes WHERE s.id = :id", Schedules.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Schedules> findAllWithEntityGraph() {
        EntityGraph<Schedules> entityGraph = entityManager.createEntityGraph(Schedules.class);
        entityGraph.addAttributeNodes("classes");

        TypedQuery<Schedules> query = entityManager.createQuery(
                "select s from Schedules s", Schedules.class);
        query.setHint("javax.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
