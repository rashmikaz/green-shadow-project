package com.example.backend_spring_boot_final_project.controller;

import com.example.backend_spring_boot_final_project.dto.impl.UserDTO;
import com.example.backend_spring_boot_final_project.exception.DataPersistException;
import com.example.backend_spring_boot_final_project.secure.JWTAuthResponse;
import com.example.backend_spring_boot_final_project.secure.SignIn;
import com.example.backend_spring_boot_final_project.service.AuthService;
import com.example.backend_spring_boot_final_project.service.UserService;
import com.example.backend_spring_boot_final_project.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthUserController {
    private final UserService userService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO userDTO) {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setUserId(AppUtil.generateUserId());
            userService.saveUser(userDTO);
            return ResponseEntity.ok(authService.signUp(userDTO));
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam("existingToken") String existingToken) {
        return ResponseEntity.ok(authService.refreshToken(existingToken));
    }
}
