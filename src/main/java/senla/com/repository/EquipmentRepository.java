package senla.com.repository;

import senla.com.entity.Equipment;

import java.util.List;

public interface EquipmentRepository {
    Equipment findById(Long id);

    List<Equipment> findAll();

    void save(Equipment equipment);

    void deleteById(Long id);
}
