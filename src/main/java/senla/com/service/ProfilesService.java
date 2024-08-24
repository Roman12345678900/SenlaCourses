package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.ProfilesDto;

import java.util.List;

@Component
public interface ProfilesService {
    ProfilesDto findById(Long id);

    List<ProfilesDto> findAll();

    void save(ProfilesDto profilesDto);

    void deleteById(Long id);
}
