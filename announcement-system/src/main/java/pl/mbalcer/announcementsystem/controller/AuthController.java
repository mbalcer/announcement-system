package pl.mbalcer.announcementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mbalcer.announcementsystem.payload.request.LoginRequest;
import pl.mbalcer.announcementsystem.payload.request.RegisterRequest;
import pl.mbalcer.announcementsystem.service.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }
}
