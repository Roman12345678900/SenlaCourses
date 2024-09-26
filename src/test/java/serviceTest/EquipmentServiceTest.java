package serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.dto.EquipmentDto;
import senla.com.entity.Equipment;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentRepository;
import senla.com.service.implementation.EquipmentServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Before
    public void setUp() {
        equipmentRepository = Mockito.mock(EquipmentRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        equipmentService = new EquipmentServiceImpl(equipmentRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName("Treadmill");
        equipment.setDescription("A high-quality treadmill");
        equipment.setPurchaseDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        equipment.setStatus("Active");

        when(equipmentRepository.findById(id)).thenReturn(Optional.of(equipment));

        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setId(id);
        equipmentDto.setName("Treadmill");
        equipmentDto.setDescription("A high-quality treadmill");
        equipmentDto.setStatus("Active");

        when(genericMapper.convertToDto(equipment, EquipmentDto.class)).thenReturn(equipmentDto);

        EquipmentDto result = equipmentService.findById(id);

        verify(equipmentRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(equipment, EquipmentDto.class);

        assertEquals(id, result.getId());
        assertEquals("Treadmill", result.getName());
        assertEquals("A high-quality treadmill", result.getDescription());
        assertEquals("Active", result.getStatus());
    }

    @Test
    public void testFindAll() {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment(1L, "Treadmill", "A high-quality treadmill", Timestamp.valueOf("2024-01-01 10:00:00"), "Active"));
        equipmentList.add(new Equipment(2L, "Dumbbells", "Set of dumbbells", Timestamp.valueOf("2024-02-01 10:00:00"), "Inactive"));

        when(equipmentRepository.findAll()).thenReturn(equipmentList);
        List<EquipmentDto> result = equipmentService.findAll();

        assertEquals(equipmentList.size(), result.size());
        verify(equipmentRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Equipment equipment = new Equipment();
        equipment.setName("Treadmill");
        equipment.setDescription("A high-quality treadmill");
        equipment.setPurchaseDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        equipment.setStatus("Active");

        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setName("Treadmill");
        equipmentDto.setDescription("A high-quality treadmill");
        equipmentDto.setStatus("Active");

        when(genericMapper.convertToEntity(equipmentDto, Equipment.class)).thenReturn(equipment);

        equipmentService.save(equipmentDto);

        verify(genericMapper, times(1)).convertToEntity(equipmentDto, Equipment.class);
        verify(equipmentRepository, times(1)).save(equipment);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        equipmentService.deleteById(id);
        verify(equipmentRepository, times(1)).deleteById(id);
    }
}
