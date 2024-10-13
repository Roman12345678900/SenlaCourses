package senla.com.service;

import senla.com.dto.AuthRequest;
import senla.com.dto.AuthResponse;

public interface AuthenticationService {

    AuthResponse authenticate(AuthRequest request);
    AuthResponse registration(AuthRequest request);
}
