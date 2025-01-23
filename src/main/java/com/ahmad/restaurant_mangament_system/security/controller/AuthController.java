package com.ahmad.restaurant_mangament_system.security.controller;

import com.ahmad.restaurant_mangament_system.security.dto.*;
import com.ahmad.restaurant_mangament_system.security.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.register(signUpDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<AuthResponse> generateRefreshToken(@RequestBody @Valid TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateRefreshToken(tokenRequest));
    }

    @PatchMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request, @Valid Principal user) {
        authService.changePassword(request, user);
        return ResponseEntity.ok().build();
    }
}
