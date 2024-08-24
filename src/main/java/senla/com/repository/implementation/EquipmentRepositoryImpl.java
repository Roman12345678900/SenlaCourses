package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.Equipment;
import senla.com.repository.AbstractRepository;
import senla.com.repository.EquipmentRepository;

@Repository
public class EquipmentRepositoryImpl extends AbstractRepository<Equipment> implements EquipmentRepository {
}
