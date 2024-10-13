package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.com.configuration.AccountUserDetailsConfig;
import senla.com.dto.AuthRequest;
import senla.com.dto.AuthResponse;
import senla.com.entity.User;
import senla.com.repository.RoleRepository;
import senla.com.repository.UserRepository;
import senla.com.security.JwtService;
import senla.com.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = Optional.ofNullable(userRepository.findByEmail(request.getEmail())).orElseThrow();
        String jwtToken = jwtService.generateToken(new AccountUserDetailsConfig(user));

        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse registration(AuthRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(roleRepository.findByName("USER")))
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(new AccountUserDetailsConfig(user));

        return AuthResponse.builder().token(jwtToken).build();
    }
}
