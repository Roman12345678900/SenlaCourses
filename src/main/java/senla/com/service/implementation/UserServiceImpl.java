package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.UserDto;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.UserRepository;
import senla.com.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GenericMapper genericMapper;

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        return genericMapper.convertToDto(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> genericMapper.convertToDto(user,UserDto.class))
                .toList();
    }

    @Override
    public void save(UserDto userDto) {
        User user = genericMapper.convertToEntity(userDto,User.class);
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
