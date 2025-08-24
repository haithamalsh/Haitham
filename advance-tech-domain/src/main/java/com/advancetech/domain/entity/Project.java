package com.advancetech.domain.entity;

import com.advancetech.domain.enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Project entity
 */
@Entity
@Table(name = "projects", indexes = {
    @Index(name = "idx_projects_user", columnList = "user_id"),
    @Index(name = "idx_projects_service", columnList = "service_id"),
    @Index(name = "idx_projects_quote", columnList = "quote_request_id"),
    @Index(name = "idx_projects_status", columnList = "status"),
    @Index(name = "idx_projects_start_date", columnList = "start_date")
})
@Getter
@Setter
public class Project extends BaseEntity {
    
    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull(message = "Service is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_request_id")
    private QuoteRequest quoteRequest;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    
    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    @Column(name = "description", length = 2000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProjectStatus status = ProjectStatus.PLANNING;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "budget", precision = 10, scale = 2)
    private BigDecimal budget;
    
    @Column(name = "actual_cost", precision = 10, scale = 2)
    private BigDecimal actualCost;
    
    @Size(max = 500, message = "Team members must not exceed 500 characters")
    @Column(name = "team_members", length = 500)
    private String teamMembers; // Comma-separated list or JSON
    
    @Column(name = "progress_percentage")
    private Integer progressPercentage = 0;
    
    @Column(name = "milestones", columnDefinition = "JSON")
    private String milestones; // JSON array of milestones
    
    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;
    
    @Column(name = "priority_score")
    private Integer priorityScore = 0;
    
    // Helper methods
    public boolean isActive() {
        return status == ProjectStatus.PLANNING || status == ProjectStatus.IN_PROGRESS;
    }
    
    public boolean isCompleted() {
        return status == ProjectStatus.COMPLETED;
    }
    
    public boolean isDelayed() {
        return status == ProjectStatus.DELAYED || 
               (endDate != null && endDate.isBefore(LocalDate.now()) && !isCompleted());
    }
    
    public void updateProgress(int newProgress) {
        if (newProgress >= 0 && newProgress <= 100) {
            this.progressPercentage = newProgress;
            if (newProgress == 100) {
                this.status = ProjectStatus.COMPLETED;
            } else if (newProgress > 0) {
                this.status = ProjectStatus.IN_PROGRESS;
            }
        }
    }
}
