package com.advancetech.domain.entity;

import com.advancetech.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Appointment entity
 */
@Entity
@Table(name = "appointments", indexes = {
    @Index(name = "idx_appointments_user", columnList = "user_id"),
    @Index(name = "idx_appointments_service", columnList = "service_id"),
    @Index(name = "idx_appointments_date", columnList = "appointment_date"),
    @Index(name = "idx_appointments_status", columnList = "status")
})
@Getter
@Setter
public class Appointment extends BaseEntity {
    
    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull(message = "Service is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    
    @NotNull(message = "Appointment date is required")
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;
    
    @NotNull(message = "Appointment time is required")
    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;
    
    @Size(max = 500, message = "Address must not exceed 500 characters")
    @Column(name = "address", length = 500)
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
    
    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;
    
    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;
    
    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    @Column(name = "feedback", length = 1000)
    private String feedback;
    
    @Column(name = "cancellation_reason", length = 500)
    private String cancellationReason;
    
    @Column(name = "reminder_sent")
    private Boolean reminderSent = false;
    
    @Column(name = "confirmation_sent")
    private Boolean confirmationSent = false;
    
    // Helper methods
    public boolean canBeCancelled() {
        return status == AppointmentStatus.SCHEDULED || status == AppointmentStatus.CONFIRMED;
    }
    
    public boolean canBeConfirmed() {
        return status == AppointmentStatus.SCHEDULED;
    }
    
    public boolean canBeCompleted() {
        return status == AppointmentStatus.CONFIRMED;
    }
    
    public boolean isPast() {
        return appointmentDate.isBefore(LocalDate.now()) || 
               (appointmentDate.isEqual(LocalDate.now()) && appointmentTime.isBefore(LocalTime.now()));
    }
}
