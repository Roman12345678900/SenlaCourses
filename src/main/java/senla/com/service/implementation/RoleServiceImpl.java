package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.RoleDto;
import senla.com.entity.Role;
import senla.com.mapper.GenericMapper;
import senla.com.repository.RoleRepository;
import senla.com.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final GenericMapper genericMapper;

    @Override
    public RoleDto findById(Long id) {
        return genericMapper.convertToDto(roleRepository.findById(id),RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> genericMapper.convertToDto(role,RoleDto.class))
                .toList();
    }

    @Override
    public void save(RoleDto roleDto) {
        Role role = genericMapper.convertToEntity(roleDto,Role.class);
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
