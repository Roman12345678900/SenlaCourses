package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import senla.com.entity.Reviews;
import senla.com.entity.Reviews_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ReviewsRepository;

import java.util.List;

@Repository
public class ReviewsRepositoryImpl extends AbstractRepository<Reviews, Long> implements ReviewsRepository {

    @Override
    public Class<Reviews> getEntityclass() {
        return Reviews.class;
    }

    @Override
    public List<Reviews> findWithJpql() {
        String jpql = "select r from Reviews r left join fetch r.user";
        TypedQuery<Reviews> query = entityManager.createQuery(jpql, Reviews.class);

        return query.getResultList();
    }

    @Override
    public List<Reviews> findAllWithFetch() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reviews> cq = cb.createQuery(Reviews.class);
        Root<Reviews> root = cq.from(Reviews.class);
        root.fetch(Reviews_.classes, JoinType.LEFT);
        root.fetch(Reviews_.user, JoinType.LEFT);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Reviews> findAllWithEntityGraph() {
        EntityGraph<Reviews> graph = entityManager.createEntityGraph(Reviews.class);
        graph.addAttributeNodes("classes", "user");

        TypedQuery<Reviews> query = entityManager.createQuery("select r from Reviews r", Reviews.class);
        query.setHint("javax.persistence.loadgraph", graph);

        return query.getResultList();
    }

    @Override
    public Reviews findByIdWithAssociationsNamedEntityGraph(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("Reviews.withAssociations");
        TypedQuery<Reviews> query = entityManager.createQuery("select r from Reviews r where r.id = :id", Reviews.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getSingleResult();
    }
}
