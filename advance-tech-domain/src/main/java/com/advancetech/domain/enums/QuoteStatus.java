package com.advancetech.domain.enums;

/**
 * Quote request status enumeration
 */
public enum QuoteStatus {
    PENDING("PENDING", "في انتظار المراجعة"),
    REVIEWED("REVIEWED", "تمت المراجعة"),
    APPROVED("APPROVED", "تمت الموافقة"),
    REJECTED("REJECTED", "مرفوض");
    
    private final String code;
    private final String arabicName;
    
    QuoteStatus(String code, String arabicName) {
        this.code = code;
        this.arabicName = arabicName;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getArabicName() {
        return arabicName;
    }
    
    public static QuoteStatus fromCode(String code) {
        for (QuoteStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown quote status code: " + code);
    }
}
