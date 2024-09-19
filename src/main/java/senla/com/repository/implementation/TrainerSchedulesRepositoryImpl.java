package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import senla.com.entity.TrainerSchedules;
import senla.com.entity.TrainerSchedules_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.TrainerSchedulesRepository;

import java.util.List;

@Repository
public class TrainerSchedulesRepositoryImpl extends AbstractRepository<TrainerSchedules, Long> implements TrainerSchedulesRepository {

    @Override
    public Class<TrainerSchedules> getEntityclass() {
        return TrainerSchedules.class;
    }

    @Override
    public List<TrainerSchedules> findAllWithCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainerSchedules> criteriaQuery = criteriaBuilder.createQuery(TrainerSchedules.class);
        Root<TrainerSchedules> root = criteriaQuery.from(TrainerSchedules.class);
        root.fetch(TrainerSchedules_.user, JoinType.LEFT);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<TrainerSchedules> findAllWithJpql() {
        String jpql = "select ts from TrainerSchedules ts left join fetch ts.user";
        TypedQuery<TrainerSchedules> query = entityManager.createQuery(jpql, TrainerSchedules.class);
        return query.getResultList();
    }

    @Override
    public List<TrainerSchedules> findAllWithGraph() {
        EntityGraph<TrainerSchedules> entityGraph = entityManager.createEntityGraph(TrainerSchedules.class);
        entityGraph.addAttributeNodes("user");

        TypedQuery<TrainerSchedules> query = entityManager.createQuery(
                "select ts from TrainerSchedules ts", TrainerSchedules.class);
        query.setHint("javax.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }

}
