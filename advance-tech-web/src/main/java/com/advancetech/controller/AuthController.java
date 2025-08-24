package com.advancetech.controller;

import com.advancetech.api.dto.*;
import com.advancetech.domain.entity.User;
import com.advancetech.security.JwtUtil;
import com.advancetech.service.AuthService;
import com.advancetech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication REST Controller
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDto>> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
        if (userService.usernameExists(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Username is already taken!", "USERNAME_TAKEN"));
        }

        if (userService.emailExists(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Email Address already in use!", "EMAIL_TAKEN"));
        }

        User user = authService.registerUser(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getPhone()
        );

        UserDto userDto = mapToUserDto(user);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", userDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication.getName());

        User user = userService.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update last login
        userService.updateLastLogin(user.getId());

        UserDto userDto = mapToUserDto(user);
        LoginResponse loginResponse = new LoginResponse(jwt, refreshToken, (long) jwtUtil.getJwtExpirationMs(), userDto);

        return ResponseEntity.ok(ApiResponse.success("Login successful", loginResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@RequestBody String refreshToken) {
        try {
            if (jwtUtil.validateJwtToken(refreshToken)) {
                String username = jwtUtil.getUsernameFromJwtToken(refreshToken);
                User user = userService.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                String newAccessToken = jwtUtil.generateTokenFromUsername(username);
                String newRefreshToken = jwtUtil.generateRefreshToken(username);

                UserDto userDto = mapToUserDto(user);
                LoginResponse loginResponse = new LoginResponse(newAccessToken, newRefreshToken, 
                        (long) jwtUtil.getJwtExpirationMs(), userDto);

                return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", loginResponse));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Invalid refresh token", "INVALID_REFRESH_TOKEN"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Error refreshing token", "REFRESH_ERROR"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(ApiResponse.success("Logout successful"));
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAvatarUrl(user.getAvatarUrl());
        userDto.setVerificationStatus(user.getVerificationStatus());
        userDto.setEnabled(user.getEnabled());
        userDto.setRoles(user.getRoles());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setEmailVerified(user.getEmailVerified());
        userDto.setPhoneVerified(user.getPhoneVerified());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
