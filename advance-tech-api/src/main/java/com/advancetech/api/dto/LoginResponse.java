package com.advancetech.api.dto;

import lombok.Data;

/**
 * Response DTO for user login
 */
@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private UserDto user;
    
    public LoginResponse(String accessToken, String refreshToken, Long expiresIn, UserDto user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }
}
