package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senla.com.dto.AuthRequest;
import senla.com.dto.AuthResponse;
import senla.com.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authenticationService.authenticate(authRequest);
    }

    @PostMapping("/reg")
    public AuthResponse registration(@RequestBody AuthRequest authRequest) {
        return authenticationService.registration(authRequest);
    }
}
