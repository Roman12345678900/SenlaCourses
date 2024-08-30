package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.UserDto;

import java.util.List;

@Component
public interface UserService {
    UserDto findById(Long id);

    List<UserDto> findAll();

    void save(UserDto userDto);

    void deleteById(Long id);
}
