package com.ahmad.restaurant_mangament_system.security.services.impl;

import com.ahmad.restaurant_mangament_system.enums.ErrorMessage;
import com.ahmad.restaurant_mangament_system.exception.NotFoundException;
import com.ahmad.restaurant_mangament_system.security.dto.*;
import com.ahmad.restaurant_mangament_system.security.enums.Role;
import com.ahmad.restaurant_mangament_system.security.model.User;
import com.ahmad.restaurant_mangament_system.security.repository.UserRepository;
import com.ahmad.restaurant_mangament_system.security.services.AuthService;
import com.ahmad.restaurant_mangament_system.security.services.JwtService;
import com.ahmad.restaurant_mangament_system.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtil mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(SignUpDto signUpDto) {
        isUserExists(signUpDto.getEmail());
        User user = mapper.mapEntity(signUpDto, User.class);
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        setUpUser(user);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponse(jwtToken, refreshToken);
    }

    public void setUpUser(User user) {

    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                ));

        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(jwtToken,refreshToken);
    }

    @Override
    public AuthResponse generateRefreshToken(TokenRequest tokenRequest) {
        String email = jwtService.extractUsername(tokenRequest.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if (jwtService.isTokenValid(tokenRequest.getToken(),user)) {
            String jwt = jwtService.generateToken(user);
            return new AuthResponse(jwt,tokenRequest.getToken());
        }
        return null;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong Password");
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    private void isUserExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new NotFoundException(HttpStatus.FORBIDDEN.value(), ErrorMessage.USER_ALREADY_EXISTS.getMessage());
        }
    }
}
