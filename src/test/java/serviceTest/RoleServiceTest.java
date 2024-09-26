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
import senla.com.dto.RoleDto;
import senla.com.entity.Role;
import senla.com.mapper.GenericMapper;
import senla.com.repository.RoleRepository;
import senla.com.service.implementation.RoleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Before
    public void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        roleService = new RoleServiceImpl(roleRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Role role = new Role();
        role.setId(id);
        role.setName("Admin");

        when(roleRepository.findById(id)).thenReturn(Optional.of(role));

        RoleDto roleDto = new RoleDto();
        roleDto.setId(id);
        roleDto.setName("Admin");
        when(genericMapper.convertToDto(role, RoleDto.class)).thenReturn(roleDto);

        RoleDto result = roleService.findById(id);

        verify(roleRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(role, RoleDto.class);

        assertEquals(id, result.getId());
        assertEquals(role.getName(), result.getName());
    }

    @Test
    public void testFindAll() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "Admin"));
        roles.add(new Role(2L, "User"));

        when(roleRepository.findAll()).thenReturn(roles);
        List<RoleDto> result = roleService.findAll();

        assertEquals(roles.size(), result.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Role role = new Role();
        role.setName("Admin");

        RoleDto roleDto = new RoleDto();
        roleDto.setName("Admin");

        when(genericMapper.convertToEntity(roleDto, Role.class)).thenReturn(role);

        roleService.save(roleDto);

        verify(genericMapper, times(1)).convertToEntity(roleDto, Role.class);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        roleService.deleteById(id);
        verify(roleRepository, times(1)).deleteById(id);
    }
}
