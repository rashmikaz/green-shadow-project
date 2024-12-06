package com.example.backend_spring_boot_final_project.service;

import com.example.backend_spring_boot_final_project.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(UserDTO userDTO);

    UserDetailsService userDetailsService();
}
