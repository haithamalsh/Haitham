package com.advancetech.api.dto;

import com.advancetech.domain.enums.ProjectStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Project Data Transfer Object
 */
@Data
public class ProjectDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private String userEmail;
    private Long serviceId;
    private String serviceNameEn;
    private String serviceNameAr;
    private Long quoteRequestId;
    private String title;
    private String description;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;
    private BigDecimal actualCost;
    private String teamMembers;
    private Integer progressPercentage;
    private String milestones;
    private String notes;
    private Integer priorityScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
