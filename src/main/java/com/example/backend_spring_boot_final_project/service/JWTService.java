package com.example.backend_spring_boot_final_project.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String refreshToken(UserDetails userDetails);

    String generateToken(UserDetails userDetails);
}
