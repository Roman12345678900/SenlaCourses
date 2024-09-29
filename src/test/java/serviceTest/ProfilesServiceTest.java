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
import senla.com.dto.ProfilesDto;
import senla.com.entity.Profiles;
import senla.com.entity.TrainerSchedules;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ProfilesRepository;
import senla.com.service.implementation.ProfilesServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class ProfilesServiceTest {

    @Mock
    private ProfilesRepository profilesRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private ProfilesServiceImpl profilesService;

    @Before
    public void setUp() {
        profilesRepository = Mockito.mock(ProfilesRepository.class);
        genericMapper = Mockito.mock(GenericMapper.class);
        profilesService = new ProfilesServiceImpl(profilesRepository, genericMapper);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Profiles profile = new Profiles();
        profile.setId(id);
        profile.setAddress("123 Main St");
        profile.setDateOfBirth(Date.valueOf("1990-01-01"));
        profile.setGender("Male");
        profile.setPhone("123-456-7890");

        User user = new User();
        profile.setUser(user);

        when(profilesRepository.findById(id)).thenReturn(Optional.of(profile));

        ProfilesDto profileDto = new ProfilesDto();
        profileDto.setId(id);
        profileDto.setAddress("123 Main St");
        profileDto.setDateOfBirth(Date.valueOf("1990-01-01"));
        profileDto.setGender("Male");
        profileDto.setPhone("123-456-7890");
        when(genericMapper.convertToDto(profile, ProfilesDto.class)).thenReturn(profileDto);

        ProfilesDto result = profilesService.findById(id);

        verify(profilesRepository, times(1)).findById(id);
        verify(genericMapper, times(1)).convertToDto(profile, ProfilesDto.class);

        assertEquals(id, result.getId());
        assertEquals(profile.getAddress(), result.getAddress());
        assertEquals(profile.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(profile.getGender(), result.getGender());
        assertEquals(profile.getPhone(), result.getPhone());
    }

    @Test
    public void testFindAll() {
        List<Profiles> profilesList = new ArrayList<>();
        profilesList.add(new Profiles(1L, "123 Main St", Date.valueOf("1990-01-01"), "Male", "123-456-7890", null));
        profilesList.add(new Profiles(2L, "456 Another St", Date.valueOf("1992-02-02"), "Female", "987-654-3210", null));

        when(profilesRepository.findAll()).thenReturn(profilesList);
        List<ProfilesDto> result = profilesService.findAll();

        assertEquals(profilesList.size(), result.size());
        verify(profilesRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Profiles profile = new Profiles();
        profile.setAddress("123 Main St");
        profile.setDateOfBirth(Date.valueOf("1990-01-01"));
        profile.setGender("Male");
        profile.setPhone("123-456-7890");

        User user = new User();
        profile.setUser(user);

        ProfilesDto profileDto = new ProfilesDto();
        profileDto.setAddress("123 Main St");
        profileDto.setDateOfBirth(Date.valueOf("1990-01-01"));
        profileDto.setGender("Male");
        profileDto.setPhone("123-456-7890");

        when(genericMapper.convertToEntity(profileDto, Profiles.class)).thenReturn(profile);

        profilesService.save(profileDto);

        verify(genericMapper, times(1)).convertToEntity(profileDto, Profiles.class);
        verify(profilesRepository, times(1)).save(profile);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        Profiles profiles = new Profiles();
        profiles.setId(id);

        when(profilesRepository.findById(id)).thenReturn(Optional.of(profiles));

        profilesService.deleteById(id);

        verify(profilesRepository, times(1)).deleteById(id);
    }
}
