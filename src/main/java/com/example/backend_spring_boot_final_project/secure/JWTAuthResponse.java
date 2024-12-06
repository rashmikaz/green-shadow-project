package com.example.backend_spring_boot_final_project.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTAuthResponse {
    private String token;
}
