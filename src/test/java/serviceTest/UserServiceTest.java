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
import senla.com.dto.UserDto;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.UserRepository;
import senla.com.service.implementation.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        userService = new UserServiceImpl(userRepository, genericMapper);
    }

    @Test
    public void TestFindById(){
        Long id = 1L;
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto();
        userDto.setId(id);
        when(genericMapper.convertToDto(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.findById(id);

        verify(userRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(user, UserDto.class);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> result = userService.findAll();

        assertEquals(users.size(), result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setFirstName("Test User");

        UserDto userDto = new UserDto();
        userDto.setFirstName("Test User");

        when(genericMapper.convertToEntity(userDto, User.class)).thenReturn(user);

        userService.save(userDto);

        verify(genericMapper, times(1)).convertToEntity(userDto, User.class);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        UserDto userDto = new UserDto();
        userDto.setFirstName("New Name");
        userDto.setLastName("New LastName");
        userDto.setEmail("newemail.gmail");
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        boolean result = userService.update(id, userDto);

        assertTrue(result);
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getEmail(), user.getEmail());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        userService.deleteById(id);
        verify(userRepository, times(1)).deleteById(id);
    }
}
