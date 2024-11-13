package com.example.backend_spring_boot_final_project.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String user_id;
    private String email;
    private String password;
    private Role role;
}
