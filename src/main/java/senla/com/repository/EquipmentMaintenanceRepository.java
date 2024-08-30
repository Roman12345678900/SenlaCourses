package senla.com.repository;

import org.springframework.stereotype.Repository;
import senla.com.entity.EquipmentMaintenance;

import java.util.List;

public interface EquipmentMaintenanceRepository {

    EquipmentMaintenance findById(Long id);

    List<EquipmentMaintenance> findAll();

    void save(EquipmentMaintenance equipmentMaintenance);

    void deleteById(Long id);
}
