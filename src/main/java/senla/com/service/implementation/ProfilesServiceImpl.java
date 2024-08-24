package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.ProfilesDto;
import senla.com.entity.Profiles;
import senla.com.mapper.GenericMapper;
import senla.com.repository.ProfilesRepository;
import senla.com.service.ProfilesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfilesRepository profilesRepository;
    private final GenericMapper genericMapper;

    @Override
    public ProfilesDto findById(Long id) {
        return genericMapper.convertToDto(profilesRepository.findById(id),ProfilesDto.class);
    }

    @Override
    public List<ProfilesDto> findAll() {
        return profilesRepository.findAll().stream()
                .map(profiles -> genericMapper.convertToDto(profiles,ProfilesDto.class))
                .toList();
    }

    @Override
    public void save(ProfilesDto profilesDto) {
        Profiles profiles = genericMapper.convertToEntity(profilesDto,Profiles.class);
        profilesRepository.save(profiles);
    }

    @Override
    public void deleteById(Long id) {
        profilesRepository.deleteById(id);
    }
}
