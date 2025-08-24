package com.advancetech.api.dto;

import com.advancetech.domain.enums.AppointmentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Appointment Data Transfer Object
 */
@Data
public class AppointmentDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private String userEmail;
    private Long serviceId;
    private String serviceNameEn;
    private String serviceNameAr;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String address;
    private AppointmentStatus status;
    private String notes;
    private BigDecimal rating;
    private String feedback;
    private String cancellationReason;
    private Boolean reminderSent;
    private Boolean confirmationSent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
