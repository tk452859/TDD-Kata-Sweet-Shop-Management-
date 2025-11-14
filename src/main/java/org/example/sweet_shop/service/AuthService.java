package org.example.sweet_shop.service;

import lombok.RequiredArgsConstructor;
import org.example.sweet_shop.dto.AuthResponse;
import org.example.sweet_shop.entity.User;
import org.example.sweet_shop.jwt.JwtUtil;
import org.example.sweet_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public AuthResponse registerUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // In real app, encode this!
        user.setRole("CUSTOMER");

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole());

        return new AuthResponse(token, savedUser.getEmail(), savedUser.getRole());
    }

    public AuthResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }


}
