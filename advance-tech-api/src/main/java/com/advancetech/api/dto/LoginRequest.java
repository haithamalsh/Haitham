package com.advancetech.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Request DTO for user login
 */
@Data
public class LoginRequest {
    
    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail;
    
    @NotBlank(message = "Password is required")
    private String password;
}
