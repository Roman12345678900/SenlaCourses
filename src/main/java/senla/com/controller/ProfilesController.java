package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.ProfilesDto;
import senla.com.service.ProfilesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfilesService profilesService;

    @GetMapping("/{id}")
    public ProfilesDto findById(@PathVariable("id") Long id) {
        return profilesService.findById(id);
    }

    @GetMapping
    public List<ProfilesDto> findAll() {
        return profilesService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ProfilesDto profilesDto) {
        profilesService.save(profilesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        profilesService.deleteById(id);
    }
}
