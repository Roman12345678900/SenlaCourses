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
import senla.com.dto.EquipmentMaintenanceDto;
import senla.com.entity.Equipment;
import senla.com.entity.EquipmentMaintenance;
import senla.com.entity.TrainerSchedules;
import senla.com.mapper.GenericMapper;
import senla.com.repository.EquipmentMaintenanceRepository;
import senla.com.service.implementation.EquipmentMaintenanceServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class EquipmentMaintenanceServiceTest {

    @Mock
    private EquipmentMaintenanceRepository equipmentMaintenanceRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private EquipmentMaintenanceServiceImpl equipmentMaintenanceService;

    @Before
    public void setUp() {
        equipmentMaintenanceRepository = Mockito.mock(EquipmentMaintenanceRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        equipmentMaintenanceService = new EquipmentMaintenanceServiceImpl(equipmentMaintenanceRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        EquipmentMaintenance maintenance = new EquipmentMaintenance();
        maintenance.setId(id);
        maintenance.setMaintenanceDate(Date.valueOf("2024-01-01"));
        maintenance.setDescription("Annual service");

        Equipment equipment = new Equipment();
        equipment.setId(1L);
        maintenance.setEquipment(equipment);

        when(equipmentMaintenanceRepository.findById(id)).thenReturn(Optional.of(maintenance));

        EquipmentMaintenanceDto maintenanceDto = new EquipmentMaintenanceDto();
        maintenanceDto.setId(id);
        maintenanceDto.setMaintenanceDate(Date.valueOf("2024-01-01"));
        maintenanceDto.setDescription("Annual service");

        when(genericMapper.convertToDto(maintenance, EquipmentMaintenanceDto.class)).thenReturn(maintenanceDto);

        EquipmentMaintenanceDto result = equipmentMaintenanceService.findById(id);

        verify(equipmentMaintenanceRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(maintenance, EquipmentMaintenanceDto.class);

        assertEquals(id, result.getId());
        assertEquals(Date.valueOf("2024-01-01"), result.getMaintenanceDate());
        assertEquals("Annual service", result.getDescription());
    }

    @Test
    public void testFindAll() {
        List<EquipmentMaintenance> maintenanceList = new ArrayList<>();
        maintenanceList.add(new EquipmentMaintenance(1L, Date.valueOf("2024-01-01"), "Annual service", null));
        maintenanceList.add(new EquipmentMaintenance(2L, Date.valueOf("2024-02-01"), "Quarterly check", null));

        when(equipmentMaintenanceRepository.findAll()).thenReturn(maintenanceList);
        List<EquipmentMaintenanceDto> result = equipmentMaintenanceService.findAll();

        assertEquals(maintenanceList.size(), result.size());
        verify(equipmentMaintenanceRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        EquipmentMaintenance maintenance = new EquipmentMaintenance();
        maintenance.setMaintenanceDate(Date.valueOf("2024-01-01"));
        maintenance.setDescription("Annual service");

        EquipmentMaintenanceDto maintenanceDto = new EquipmentMaintenanceDto();
        maintenanceDto.setMaintenanceDate(Date.valueOf("2024-01-01"));
        maintenanceDto.setDescription("Annual service");

        when(genericMapper.convertToEntity(maintenanceDto, EquipmentMaintenance.class)).thenReturn(maintenance);

        equipmentMaintenanceService.save(maintenanceDto);

        verify(genericMapper, times(1)).convertToEntity(maintenanceDto, EquipmentMaintenance.class);
        verify(equipmentMaintenanceRepository, times(1)).save(maintenance);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        EquipmentMaintenance equipmentMaintenance = new EquipmentMaintenance();
        equipmentMaintenance.setId(id);

        when(equipmentMaintenanceRepository.findById(id)).thenReturn(Optional.of(equipmentMaintenance));

        equipmentMaintenanceService.deleteById(id);

        verify(equipmentMaintenanceRepository, times(1)).deleteById(id);
    }
}
