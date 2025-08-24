package com.advancetech.domain.enums;

/**
 * User verification status enumeration
 */
public enum VerificationStatus {
    PENDING("PENDING", "في انتظار التحقق"),
    VERIFIED("VERIFIED", "تم التحقق"),
    REJECTED("REJECTED", "مرفوض");
    
    private final String code;
    private final String arabicName;
    
    VerificationStatus(String code, String arabicName) {
        this.code = code;
        this.arabicName = arabicName;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getArabicName() {
        return arabicName;
    }
    
    public static VerificationStatus fromCode(String code) {
        for (VerificationStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown verification status code: " + code);
    }
}
