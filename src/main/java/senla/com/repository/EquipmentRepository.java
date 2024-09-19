package senla.com.repository;

import senla.com.entity.Equipment;

import java.util.List;

public interface EquipmentRepository {

    List<Equipment> findByStatusWithFetchCriteria(String status);

    List<Equipment> findAllWithFetchJPQL();

    List<Equipment> findAllWithEntityGraph();
}
