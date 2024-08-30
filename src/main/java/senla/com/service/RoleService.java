package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.RoleDto;

import java.util.List;

@Component
public interface RoleService {
    RoleDto findById(Long id);

    List<RoleDto> findAll();

    void save(RoleDto roleDto);

    void deleteById(Long id);
}
