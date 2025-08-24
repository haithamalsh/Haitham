package com.advancetech.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Review Data Transfer Object
 */
@Data
public class ReviewDto {
    private Long id;
    private Long userId;
    private String userUsername;
    private Long serviceId;
    private String serviceNameEn;
    private String serviceNameAr;
    private Long projectId;
    private String projectTitle;
    private Integer rating;
    private String comment;
    private Boolean approved;
    private Integer helpfulCount;
    private Boolean reported;
    private String adminNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
