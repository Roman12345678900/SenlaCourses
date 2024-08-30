package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.UserDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(userService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(userService.findAll());
    }

    public void save(String userDto) {
        userService.save(jsonMapper.deserialize(userDto, UserDto.class));
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }
}
