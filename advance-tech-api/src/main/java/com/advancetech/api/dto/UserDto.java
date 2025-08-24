package com.advancetech.api.dto;

import com.advancetech.domain.enums.UserRole;
import com.advancetech.domain.enums.VerificationStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * User Data Transfer Object
 */
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatarUrl;
    private VerificationStatus verificationStatus;
    private Boolean enabled;
    private Set<UserRole> roles;
    private LocalDateTime lastLogin;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
