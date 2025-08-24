package com.advancetech.controller;

import com.advancetech.api.dto.ApiResponse;
import com.advancetech.api.dto.UpdateUserRequest;
import com.advancetech.api.dto.UserDto;
import com.advancetech.domain.entity.User;
import com.advancetech.security.UserPrincipal;
import com.advancetech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * User Management REST Controller
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<UserDto>> getCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userService.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto userDto = mapToUserDto(user);
        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<UserDto>> updateCurrentUser(
            @Valid @RequestBody UpdateUserRequest updateRequest,
            Authentication authentication) {
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        User userDetails = new User();
        userDetails.setPhone(updateRequest.getPhone());
        userDetails.setAvatarUrl(updateRequest.getAvatarUrl());
        
        User updatedUser = userService.updateUser(userPrincipal.getId(), userDetails);
        UserDto userDto = mapToUserDto(updatedUser);
        
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", userDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto userDto = mapToUserDto(user);
        return ResponseEntity.ok(ApiResponse.success(userDto));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> updateUserStatus(
            @PathVariable Long id,
            @RequestParam boolean enabled) {
        
        User updatedUser = userService.updateUserStatus(id, enabled);
        UserDto userDto = mapToUserDto(updatedUser);
        
        return ResponseEntity.ok(ApiResponse.success("User status updated successfully", userDto));
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
