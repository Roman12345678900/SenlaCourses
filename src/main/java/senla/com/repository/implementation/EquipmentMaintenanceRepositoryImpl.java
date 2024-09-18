package senla.com.repository.implementation;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.com.entity.EquipmentMaintenance;
import senla.com.entity.EquipmentMaintenance_;
import senla.com.repository.AbstractRepository;
import senla.com.repository.EquipmentMaintenanceRepository;

import java.util.List;

@Repository
public class EquipmentMaintenanceRepositoryImpl extends AbstractRepository<EquipmentMaintenance, Long> implements EquipmentMaintenanceRepository {

    @Override
    public Class<EquipmentMaintenance> getEntityclass() {
        return EquipmentMaintenance.class;
    }

    @Override
    public List<EquipmentMaintenance> findAllWithFetchJPQL() {
        return entityManager.createQuery(
                        "select em from EquipmentMaintenance em join fetch em.equipment", EquipmentMaintenance.class)
                .getResultList();
    }

    @Override
    public List<EquipmentMaintenance> findAllWithFetchCriteria() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EquipmentMaintenance> cq = cb.createQuery(EquipmentMaintenance.class);
        Root<EquipmentMaintenance> root = cq.from(EquipmentMaintenance.class);
        root.fetch(EquipmentMaintenance_.equipment, JoinType.LEFT);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<EquipmentMaintenance> findAllWithEntityGraph() {
        EntityGraph<EquipmentMaintenance> entityGraph = entityManager.createEntityGraph(EquipmentMaintenance.class);
        entityGraph.addAttributeNodes("equipment");

        TypedQuery<EquipmentMaintenance> query = entityManager.createQuery(
                "select em from EquipmentMaintenance em", EquipmentMaintenance.class);
        query.setHint("jakarta.persistence.loadgraph", entityGraph);

        return query.getResultList();
    }
}
