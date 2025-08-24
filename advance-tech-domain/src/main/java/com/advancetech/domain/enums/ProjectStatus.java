package com.advancetech.domain.enums;

/**
 * Project status enumeration
 */
public enum ProjectStatus {
    PLANNING("PLANNING", "في مرحلة التخطيط"),
    IN_PROGRESS("IN_PROGRESS", "قيد التنفيذ"),
    COMPLETED("COMPLETED", "مكتمل"),
    DELAYED("DELAYED", "متأخر");
    
    private final String code;
    private final String arabicName;
    
    ProjectStatus(String code, String arabicName) {
        this.code = code;
        this.arabicName = arabicName;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getArabicName() {
        return arabicName;
    }
    
    public static ProjectStatus fromCode(String code) {
        for (ProjectStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown project status code: " + code);
    }
}
