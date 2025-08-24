package com.advancetech.domain.entity;

import com.advancetech.domain.enums.QuoteStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Quote request entity
 */
@Entity
@Table(name = "quote_requests", indexes = {
    @Index(name = "idx_quotes_user", columnList = "user_id"),
    @Index(name = "idx_quotes_service", columnList = "service_id"),
    @Index(name = "idx_quotes_status", columnList = "status"),
    @Index(name = "idx_quotes_created", columnList = "created_at")
})
@Getter
@Setter
public class QuoteRequest extends BaseEntity {
    
    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull(message = "Service is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    
    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    @Column(name = "description", nullable = false, length = 2000)
    private String description;
    
    @Size(max = 500, message = "Address must not exceed 500 characters")
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "budget_range_min", precision = 10, scale = 2)
    private BigDecimal budgetRangeMin;
    
    @Column(name = "budget_range_max", precision = 10, scale = 2)
    private BigDecimal budgetRangeMax;
    
    @Size(max = 100, message = "Urgency must not exceed 100 characters")
    @Column(name = "urgency", length = 100)
    private String urgency; // LOW, MEDIUM, HIGH, URGENT
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuoteStatus status = QuoteStatus.PENDING;
    
    @Size(max = 1000, message = "Admin notes must not exceed 1000 characters")
    @Column(name = "admin_notes", length = 1000)
    private String adminNotes;
    
    @Column(name = "assigned_team")
    private String assignedTeam;
    
    @Column(name = "estimated_duration_days")
    private Integer estimatedDurationDays;
    
    @Column(name = "priority_score")
    private Integer priorityScore = 0;
    
    // Helper methods
    public boolean isInBudgetRange(BigDecimal amount) {
        if (budgetRangeMin == null && budgetRangeMax == null) {
            return true;
        }
        if (budgetRangeMin != null && amount.compareTo(budgetRangeMin) < 0) {
            return false;
        }
        if (budgetRangeMax != null && amount.compareTo(budgetRangeMax) > 0) {
            return false;
        }
        return true;
    }
    
    public boolean canBeApproved() {
        return status == QuoteStatus.REVIEWED;
    }
    
    public boolean canBeRejected() {
        return status == QuoteStatus.PENDING || status == QuoteStatus.REVIEWED;
    }
}
