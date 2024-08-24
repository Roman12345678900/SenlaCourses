package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.ProfilesDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.ProfilesService;

@Controller
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfilesService profilesService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(profilesService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(profilesService.findAll());
    }

    public void save(String profilesDto) {
        profilesService.save(jsonMapper.deserialize(profilesDto, ProfilesDto.class));
    }

    public void deleteById(Long id) {
        profilesService.deleteById(id);
    }
}
