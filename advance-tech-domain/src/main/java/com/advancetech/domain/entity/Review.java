package com.advancetech.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Review entity for service and project ratings
 */
@Entity
@Table(name = "reviews", indexes = {
    @Index(name = "idx_reviews_user", columnList = "user_id"),
    @Index(name = "idx_reviews_service", columnList = "service_id"),
    @Index(name = "idx_reviews_project", columnList = "project_id"),
    @Index(name = "idx_reviews_rating", columnList = "rating"),
    @Index(name = "idx_reviews_approved", columnList = "approved")
})
@Getter
@Setter
public class Review extends BaseEntity {
    
    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "rating", nullable = false)
    private Integer rating;
    
    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    @Column(name = "comment", length = 1000)
    private String comment;
    
    @Column(name = "approved", nullable = false)
    private Boolean approved = false;
    
    @Column(name = "helpful_count")
    private Integer helpfulCount = 0;
    
    @Column(name = "reported")
    private Boolean reported = false;
    
    @Size(max = 500, message = "Admin notes must not exceed 500 characters")
    @Column(name = "admin_notes", length = 500)
    private String adminNotes;
    
    // Validation: Either service or project must be specified
    @AssertTrue(message = "Either service or project must be specified")
    public boolean isServiceOrProjectSpecified() {
        return service != null || project != null;
    }
    
    // Helper methods
    public void markAsHelpful() {
        this.helpfulCount++;
    }
    
    public void markAsReported() {
        this.reported = true;
    }
    
    public boolean isPositive() {
        return rating >= 4;
    }
    
    public boolean isNegative() {
        return rating <= 2;
    }
    
    public boolean isNeutral() {
        return rating == 3;
    }
}
