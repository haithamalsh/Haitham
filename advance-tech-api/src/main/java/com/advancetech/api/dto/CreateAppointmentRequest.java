package com.advancetech.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request DTO for creating a new appointment
 */
@Data
public class CreateAppointmentRequest {
    
    @NotNull(message = "Service ID is required")
    private Long serviceId;
    
    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;
    
    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;
    
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;
    
    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;
}
