package com.example.backend_spring_boot_final_project.dto.impl;

import com.example.backend_spring_boot_final_project.dto.UserStatus;
import com.example.backend_spring_boot_final_project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String user_id;
    private String email;
    private String password;
    private Role role;
}
