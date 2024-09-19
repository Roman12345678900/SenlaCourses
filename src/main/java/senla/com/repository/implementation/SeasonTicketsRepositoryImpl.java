package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.SeasonTickets;
import senla.com.entity.SeasonTickets_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.SeasonTicketsRepository;

import java.util.List;

@Repository
public class SeasonTicketsRepositoryImpl extends AbstractRepository<SeasonTickets, Long> implements SeasonTicketsRepository {

    @Override
    public Class<SeasonTickets> getEntityclass() {
        return SeasonTickets.class;
    }

    @Override
    public List<SeasonTickets> findAllWithFetchCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SeasonTickets> criteriaQuery = criteriaBuilder.createQuery(SeasonTickets.class);
        Root<SeasonTickets> root = criteriaQuery.from(SeasonTickets.class);

        root.fetch(SeasonTickets_.seasonTicketsType);
        root.fetch(SeasonTickets_.user);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<SeasonTickets> findAllWithJpql() {
        return entityManager.createQuery(
                        "select st from SeasonTickets st left join fetch st.seasonTicketsType left join fetch st.user", SeasonTickets.class)
                .getResultList();
    }

    @Override
    public List<SeasonTickets> findAllWithEntityGraph() {
        EntityGraph<SeasonTickets> entityGraph = entityManager.createEntityGraph(SeasonTickets.class);
        entityGraph.addAttributeNodes("seasonTicketsType", "user");

        TypedQuery<SeasonTickets> query = entityManager.createQuery(
                "select st from SeasonTickets st", SeasonTickets.class);
        query.setHint("javax.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
