package com.advancetech.domain.enums;

/**
 * Appointment status enumeration
 */
public enum AppointmentStatus {
    SCHEDULED("SCHEDULED", "مجدول"),
    CONFIRMED("CONFIRMED", "مؤكد"),
    COMPLETED("COMPLETED", "مكتمل"),
    CANCELLED("CANCELLED", "ملغي");
    
    private final String code;
    private final String arabicName;
    
    AppointmentStatus(String code, String arabicName) {
        this.code = code;
        this.arabicName = arabicName;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getArabicName() {
        return arabicName;
    }
    
    public static AppointmentStatus fromCode(String code) {
        for (AppointmentStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown appointment status code: " + code);
    }
}
