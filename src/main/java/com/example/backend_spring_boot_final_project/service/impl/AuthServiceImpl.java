package com.example.backend_spring_boot_final_project.service.impl;

import com.example.backend_spring_boot_final_project.dao.UserDao;
import com.example.backend_spring_boot_final_project.service.AuthService;
import com.example.backend_spring_boot_final_project.service.JWTService;
import com.example.backend_spring_boot_final_project.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(user);
        System.out.println(generatedToken);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }
    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.generateUserId());
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
        //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
        System.out.println(generatedToken);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }
    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract username
        var userName = jwtService.extractUserName(accessToken);
        //check the user availability in the DB
        var findUser = userDao.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }

}
