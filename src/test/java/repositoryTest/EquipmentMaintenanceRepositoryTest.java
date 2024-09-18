package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Equipment;
import senla.com.entity.EquipmentMaintenance;
import senla.com.repository.implementation.EquipmentMaintenanceRepositoryImpl;
import senla.com.repository.implementation.EquipmentRepositoryImpl;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ApplicationConfiguration.class,
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class EquipmentMaintenanceRepositoryTest {

    @Resource
    private EquipmentMaintenanceRepositoryImpl equipmentMaintenanceRepository;

    @Resource
    private EquipmentRepositoryImpl equipmentRepository;

    @Test
    public void TestFindById() {
        Equipment equipment = Equipment.builder()
                .id(1L)
                .name("Treadmill")
                .description("Cardio equipment")
                .build();

        equipmentRepository.save(equipment);

        EquipmentMaintenance maintenance = EquipmentMaintenance.builder()
                .id(1L)
                .maintenanceDate(Date.valueOf("2024-09-19"))
                .description("Routine check-up")
                .equipment(equipment)
                .build();

        equipmentMaintenanceRepository.save(maintenance);

        EquipmentMaintenance foundMaintenance = equipmentMaintenanceRepository.findById(maintenance.getId());

        assertNotNull(foundMaintenance);
        assertEquals(maintenance.getId(), foundMaintenance.getId());
    }

    @Test
    public void TestFindAll() {
        Equipment equipment1 = Equipment.builder()
                .id(2L)
                .name("Elliptical")
                .description("Low-impact cardio machine")
                .purchaseDate(Timestamp.valueOf("2024-09-05 14:00:00"))
                .status("work")
                .build();

        equipmentRepository.save(equipment1);

        Equipment equipment2 = Equipment.builder()
                .id(3L)
                .name("Rowing Machine")
                .description("Full-body workout machine")
                .purchaseDate(Timestamp.valueOf("2024-09-05 14:00:00"))
                .status("work")
                .build();

        equipmentRepository.save(equipment2);

        EquipmentMaintenance maintenance1 = EquipmentMaintenance.builder()
                .id(2L)
                .maintenanceDate(Date.valueOf("2024-09-20"))
                .description("Replaced the belt")
                .equipment(equipment1)
                .build();

        EquipmentMaintenance maintenance2 = EquipmentMaintenance.builder()
                .id(3L)
                .maintenanceDate(Date.valueOf("2024-09-21"))
                .description("Lubricated the chain")
                .equipment(equipment2)
                .build();

        equipmentMaintenanceRepository.save(maintenance1);
        equipmentMaintenanceRepository.save(maintenance2);

        List<EquipmentMaintenance> maintenances = equipmentMaintenanceRepository.findAll();

        assertEquals(2, maintenances.size());
    }

    @Test
    public void TestSave() {
        Equipment equipment = Equipment.builder()
                .id(4L)
                .name("Stationary Bike")
                .description("Bike for indoor cycling")
                .build();

        equipmentRepository.save(equipment);

        EquipmentMaintenance maintenance = EquipmentMaintenance.builder()
                .id(4L)
                .maintenanceDate(Date.valueOf("2024-09-22"))
                .description("Checked tires and brakes")
                .equipment(equipment)
                .build();

        equipmentMaintenanceRepository.save(maintenance);

        EquipmentMaintenance savedMaintenance = equipmentMaintenanceRepository.findById(maintenance.getId());

        assertNotNull(savedMaintenance);
        assertEquals(maintenance.getEquipment().getId(), savedMaintenance.getEquipment().getId());
    }

    @Test
    public void TestDelete() {
        Equipment equipment = Equipment.builder()
                .id(5L)
                .name("Smith Machine")
                .description("Weight training equipment")
                .build();

        equipmentRepository.save(equipment);

        EquipmentMaintenance maintenance = EquipmentMaintenance.builder()
                .id(5L)
                .maintenanceDate(Date.valueOf("2024-09-23"))
                .description("Checked weights and safety")
                .equipment(equipment)
                .build();

        equipmentMaintenanceRepository.save(maintenance);

        equipmentMaintenanceRepository.deleteById(maintenance.getId());

        EquipmentMaintenance deletedMaintenance = equipmentMaintenanceRepository.findById(maintenance.getId());

        assertNull(deletedMaintenance);
    }
}
