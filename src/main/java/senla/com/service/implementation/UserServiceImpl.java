package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.dto.UserDto;
import senla.com.entity.User;
import senla.com.mapper.GenericMapper;
import senla.com.repository.GenericRepository;
import senla.com.repository.UserRepository;
import senla.com.service.UserService;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final GenericRepository<User, Long> userRepository;
    private final GenericMapper genericMapper;

    @Transactional
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        return genericMapper.convertToDto(user, UserDto.class);
    }

    @Transactional
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> genericMapper.convertToDto(user, UserDto.class))
                .toList();
    }

    @Transactional
    @Override
    public void save(UserDto userDto) {
        User user = genericMapper.convertToEntity(userDto, User.class);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public boolean update(Long id, UserDto userDto) {
        User user = userRepository.findById(id);
        if (user != null) {
            if (!userDto.getFirstName().isEmpty()) {
                user.setFirstName(userDto.getFirstName());
            }
            if (!userDto.getLastName().isEmpty()) {
                user.setLastName(userDto.getLastName());
            }
            if (!userDto.getEmail().isEmpty()) {
                user.setEmail(userDto.getEmail());
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
