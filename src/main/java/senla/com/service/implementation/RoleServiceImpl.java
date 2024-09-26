package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.RoleDto;
import senla.com.entity.Role;
import senla.com.exception.RoleNotFoundException;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.RoleRepository;
import senla.com.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final GenericRepository<Role, Long> roleRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new RoleNotFoundException(id));
        return genericMapper.convertToDto(role,RoleDto.class);
    }

    @Override
    @Transactional
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> genericMapper.convertToDto(role,RoleDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(RoleDto roleDto) {
        Role role = genericMapper.convertToEntity(roleDto,Role.class);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
