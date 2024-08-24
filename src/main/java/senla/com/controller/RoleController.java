package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.RoleDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.RoleService;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(roleService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(roleService.findAll());
    }

    public void save(String roleDto) {
        roleService.save(jsonMapper.deserialize(roleDto, RoleDto.class));
    }

    public void deleteById(Long id) {
        roleService.deleteById(id);
    }
}
