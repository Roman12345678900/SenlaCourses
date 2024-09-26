package repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.entity.Equipment;
import senla.com.repository.implementation.EquipmentRepositoryImpl;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
@Transactional
public class EquipmentRepositoryTest {

    @Resource
    private EquipmentRepositoryImpl equipmentRepository;

    @Test
    public void TestFindById() {
        Equipment equipment = Equipment.builder()
                .id(1L)
                .name("Treadmill")
                .description("Cardio equipment")
                .purchaseDate(Timestamp.valueOf("2024-09-01 10:00:00"))
                .status("Active")
                .build();

        equipmentRepository.save(equipment);

        Optional<Equipment> foundEquipment = equipmentRepository.findById(equipment.getId());

        assertNotNull(foundEquipment);
        assertEquals(equipment.getId(), foundEquipment.get().getId());
    }

    @Test
    public void TestFindAll() {
        Equipment equipment1 = Equipment.builder()
                .id(2L)
                .name("Elliptical")
                .description("Low-impact cardio machine")
                .purchaseDate(Timestamp.valueOf("2024-09-02 11:00:00"))
                .status("Active")
                .build();

        equipmentRepository.save(equipment1);

        Equipment equipment2 = Equipment.builder()
                .id(3L)
                .name("Rowing Machine")
                .description("Full-body workout machine")
                .purchaseDate(Timestamp.valueOf("2024-09-03 12:00:00"))
                .status("Inactive")
                .build();

        equipmentRepository.save(equipment2);

        List<Equipment> equipmentList = equipmentRepository.findAll();

        assertEquals(2, equipmentList.size());
    }

    @Test
    public void TestSave() {
        Equipment equipment = Equipment.builder()
                .id(4L)
                .name("Stationary Bike")
                .description("Bike for indoor cycling")
                .purchaseDate(Timestamp.valueOf("2024-09-04 13:00:00"))
                .status("Active")
                .build();

        equipmentRepository.save(equipment);

        Optional<Equipment> savedEquipment = equipmentRepository.findById(equipment.getId());

        assertNotNull(savedEquipment);
        assertEquals(equipment.getName(), savedEquipment.get().getName());
    }

    @Test
    public void TestDelete() {
        Equipment equipment = Equipment.builder()
                .id(5L)
                .name("Smith Machine")
                .description("Weight training equipment")
                .purchaseDate(Timestamp.valueOf("2024-09-05 14:00:00"))
                .status("Active")
                .build();

        equipmentRepository.save(equipment);

        equipmentRepository.deleteById(equipment.getId());

        Optional<Equipment> deletedEquipment = equipmentRepository.findById(equipment.getId());

        assertTrue(deletedEquipment.isEmpty());
    }
}
