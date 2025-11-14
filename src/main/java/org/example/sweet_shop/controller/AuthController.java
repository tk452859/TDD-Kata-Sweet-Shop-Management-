package org.example.sweet_shop.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sweet_shop.dto.AuthRequest;
import org.example.sweet_shop.dto.AuthResponse;
import org.example.sweet_shop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        System.out.println("Register endpoint hit: " + request.getEmail());
        AuthResponse response = authService.registerUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        System.out.println("Login endpoint hit: " + request.getEmail());
        AuthResponse response = authService.loginUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }

}
