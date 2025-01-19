package com.ahmad.restaurant_mangament_system.security.services;

import com.ahmad.restaurant_mangament_system.security.dto.*;

import java.security.Principal;

public interface AuthService {

     AuthResponse register(SignUpDto signUpDto);
     AuthResponse authenticate(AuthRequest authRequest);
     AuthResponse generateRefreshToken(TokenRequest tokenRequest);
     void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser);
}
