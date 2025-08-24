package com.advancetech.api.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Request DTO for updating user profile
 */
@Data
public class UpdateUserRequest {
    
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;
    
    @Size(max = 500, message = "Avatar URL must not exceed 500 characters")
    private String avatarUrl;
}
