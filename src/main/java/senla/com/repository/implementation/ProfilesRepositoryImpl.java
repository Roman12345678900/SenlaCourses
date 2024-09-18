package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Profiles;
import senla.com.entity.Profiles_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.ProfilesRepository;

import java.util.List;

@Repository
public class ProfilesRepositoryImpl extends AbstractRepository<Profiles, Long> implements ProfilesRepository {

    @Override
    public Class<Profiles> getEntityclass() {
        return Profiles.class;
    }

    @Override
    public List<Profiles> findWithJpql(){
        String jpql = "Select p from Profiles p left join fetch p.user";
        TypedQuery<Profiles> query = entityManager.createQuery(jpql, Profiles.class);

        return query.getResultList();
    }

    @Override
    public List<Profiles> findWithCriteriaApi(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profiles> cq = cb.createQuery(Profiles.class);
        Root<Profiles> root = cq.from(Profiles.class);
        root.fetch(Profiles_.user, JoinType.LEFT);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Profiles> findWithEntityGraph() {
        EntityGraph<Profiles> entityGraph = entityManager.createEntityGraph(Profiles.class);
        entityGraph.addAttributeNodes("user");

        TypedQuery<Profiles> query = entityManager.createQuery(
                "select p from Profiles p", Profiles.class);
        query.setHint("jakarta.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
