package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.SeasonTicketsType;
import senla.com.repository.AbstractRepository;
import senla.com.repository.SeasonTicketsTypeRepository;

import java.util.List;

@Repository
public class SeasonTicketsTypeRepositoryImpl extends AbstractRepository<SeasonTicketsType, Long> implements SeasonTicketsTypeRepository {

    @Override
    public Class<SeasonTicketsType> getEntityclass() {
        return SeasonTicketsType.class;
    }

    @Override
    public List<SeasonTicketsType> findAllWithCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SeasonTicketsType> criteriaQuery = criteriaBuilder.createQuery(SeasonTicketsType.class);
        Root<SeasonTicketsType> root = criteriaQuery.from(SeasonTicketsType.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<SeasonTicketsType> findAllWithJpql() {
        String jpql = "select stt from SeasonTicketsType stt";
        TypedQuery<SeasonTicketsType> query = entityManager.createQuery(jpql, SeasonTicketsType.class);

        return query.getResultList();
    }

    @Override
    public List<SeasonTicketsType> findAllWithGraph() {
        EntityGraph<SeasonTicketsType> entityGraph = entityManager.createEntityGraph(SeasonTicketsType.class);
        TypedQuery<SeasonTicketsType> query = entityManager.createQuery(
                "select stt from SeasonTicketsType stt", SeasonTicketsType.class);
        query.setHint("javax.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
