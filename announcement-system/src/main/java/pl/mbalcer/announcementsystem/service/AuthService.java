package pl.mbalcer.announcementsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.payload.request.LoginRequest;
import pl.mbalcer.announcementsystem.payload.request.RegisterRequest;

@Service
public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(RegisterRequest registerRequest);
}
