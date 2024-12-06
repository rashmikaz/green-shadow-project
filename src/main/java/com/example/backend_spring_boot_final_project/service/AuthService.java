package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.impl.UserDTO;
import com.example.backend_spring_boot_final_project.secure.JWTAuthResponse;
import com.example.backend_spring_boot_final_project.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);

    JWTAuthResponse signUp(UserDTO userDTO);

    JWTAuthResponse refreshToken(String accessToken);
}
