package senla.com.repository;

import senla.com.entity.EquipmentMaintenance;

import java.util.List;

public interface EquipmentMaintenanceRepository extends GenericRepository<EquipmentMaintenance, Long> {


    List<EquipmentMaintenance> findAllWithFetchJPQL();

    List<EquipmentMaintenance> findAllWithFetchCriteria();

    List<EquipmentMaintenance> findAllWithEntityGraph();
}
