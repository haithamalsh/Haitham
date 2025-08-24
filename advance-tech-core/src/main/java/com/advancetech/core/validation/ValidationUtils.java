package com.advancetech.core.validation;

import java.util.regex.Pattern;

/**
 * Utility class for common validation operations
 */
public final class ValidationUtils {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[+]?[0-9]{10,15}$");
    
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
    
    private ValidationUtils() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Validate email format
     * 
     * @param email The email to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validate phone number format
     * 
     * @param phone The phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * Validate password strength
     * 
     * @param password The password to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Check if string is not null and not empty
     * 
     * @param str The string to check
     * @return true if not null and not empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Check if string length is within range
     * 
     * @param str The string to check
     * @param minLength Minimum length
     * @param maxLength Maximum length
     * @return true if length is within range
     */
    public static boolean isLengthInRange(String str, int minLength, int maxLength) {
        return str != null && str.length() >= minLength && str.length() <= maxLength;
    }
}
