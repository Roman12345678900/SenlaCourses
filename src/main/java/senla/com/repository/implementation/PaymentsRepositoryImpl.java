package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Payments;
import senla.com.entity.Payments_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.PaymentsRepository;

import java.util.List;

@Repository
public class PaymentsRepositoryImpl extends AbstractRepository<Payments, Long> implements PaymentsRepository {

    @Override
    public Class<Payments> getEntityclass() {
        return Payments.class;
    }

    @Override
    public List<Payments> findAllWithJpql() {
        String jpql = "select p from Payments p join fetch p.user";
        TypedQuery<Payments> query = entityManager.createQuery(jpql, Payments.class);

        return query.getResultList();
    }

    @Override
    public List<Payments> findWithCriteriaApi(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payments> cq = cb.createQuery(Payments.class);
        Root<Payments> root= cq.from(Payments.class);
        root.fetch(Payments_.user, JoinType.LEFT);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Payments> findWithEntityGraph(){
        EntityGraph<Payments> entityGraph = entityManager.createEntityGraph(Payments.class);
        entityGraph.addAttributeNodes("user");

        TypedQuery<Payments> query = entityManager.createQuery(
                "select p from Payments p", Payments.class);
        query.setHint("jakarta.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
