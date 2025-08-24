package com.advancetech.api.dto;

import com.advancetech.domain.enums.QuoteStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Quote Request Data Transfer Object
 */
@Data
public class QuoteRequestDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private String userEmail;
    private Long serviceId;
    private String serviceNameEn;
    private String serviceNameAr;
    private String description;
    private String address;
    private BigDecimal budgetRangeMin;
    private BigDecimal budgetRangeMax;
    private String urgency;
    private QuoteStatus status;
    private String adminNotes;
    private String assignedTeam;
    private Integer estimatedDurationDays;
    private Integer priorityScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
