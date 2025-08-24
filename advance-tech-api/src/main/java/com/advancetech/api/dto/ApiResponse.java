package com.advancetech.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Generic API Response wrapper
 */
@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp;
    
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public ApiResponse(boolean success, String message) {
        this();
        this.success = success;
        this.message = message;
    }
    
    public ApiResponse(boolean success, String message, T data) {
        this(success, message);
        this.data = data;
    }
    
    public ApiResponse(boolean success, String message, String errorCode) {
        this(success, message);
        this.errorCode = errorCode;
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", data);
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }
    
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message);
    }
    
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message);
    }
    
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponse<>(false, message, errorCode);
    }
}
