package com.advancetech.domain.enums;

/**
 * User roles enumeration
 */
public enum UserRole {
    USER("USER", "مستخدم عادي"),
    MODERATOR("MODERATOR", "مشرف"),
    ADMIN("ADMIN", "مدير");
    
    private final String code;
    private final String arabicName;
    
    UserRole(String code, String arabicName) {
        this.code = code;
        this.arabicName = arabicName;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getArabicName() {
        return arabicName;
    }
    
    public static UserRole fromCode(String code) {
        for (UserRole role : values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role code: " + code);
    }
}
