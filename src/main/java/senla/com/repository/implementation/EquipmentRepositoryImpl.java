package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.Equipment;
import senla.com.entity.Equipment_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.EquipmentRepository;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl extends AbstractRepository<Equipment, Long> implements EquipmentRepository {

    @Override
    public Class<Equipment> getEntityclass() {
        return Equipment.class;
    }

    @Override
    public List<Equipment> findByStatusWithFetchCriteria(String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Equipment> cq = cb.createQuery(Equipment.class);
        Root<Equipment> equipmentRoot = cq.from(Equipment.class);
        cq.select(equipmentRoot).where(cb.equal(equipmentRoot.get(Equipment_.status), status));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Equipment> findAllWithFetchJPQL() {
        TypedQuery<Equipment> query = entityManager.createQuery(
                "select e from Equipment e", Equipment.class);

        return query.getResultList();
    }

    @Override
    public List<Equipment> findAllWithEntityGraph() {
        EntityGraph<Equipment> entityGraph = entityManager.createEntityGraph(Equipment.class);
        TypedQuery<Equipment> query = entityManager.createQuery(
                "select e from Equipment e", Equipment.class);
        query.setHint("jakarta.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
