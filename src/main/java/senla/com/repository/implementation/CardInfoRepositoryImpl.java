package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.CardInfo;
import senla.com.entity.CardInfo_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.CardInfoRepository;

import java.util.List;

@Repository
public class CardInfoRepositoryImpl extends AbstractRepository<CardInfo, Long> implements CardInfoRepository {

    @Override
    public Class<CardInfo> getEntityclass() {
        return CardInfo.class;
    }

    @Override
    public List<CardInfo> findAllWithUserCriteria() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CardInfo> cq = cb.createQuery(CardInfo.class);
        Root<CardInfo> cardInfo = cq.from(CardInfo.class);
        cardInfo.fetch(CardInfo_.user, JoinType.LEFT);
        cq.select(cardInfo);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<CardInfo> findAllWithUserJPQL() {
        String jpql = "select ci from CardInfo ci left join fetch ci.user";
        TypedQuery<CardInfo> query = entityManager.createQuery(jpql, CardInfo.class);

        return query.getResultList();
    }

    @Override
    public List<CardInfo> findAllWithUserEntityGraph() {
        EntityGraph<CardInfo> entityGraph = entityManager.createEntityGraph(CardInfo.class);
        return entityManager.createQuery("select ci from CardInfo ci", CardInfo.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();
    }
}
