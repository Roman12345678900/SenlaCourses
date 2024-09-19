package senla.com.repository;

import senla.com.entity.EquipmentMaintenance;

import java.util.List;

public interface EquipmentMaintenanceRepository {


    List<EquipmentMaintenance> findAllWithFetchJPQL();

    List<EquipmentMaintenance> findAllWithFetchCriteria();

    List<EquipmentMaintenance> findAllWithEntityGraph();
}
