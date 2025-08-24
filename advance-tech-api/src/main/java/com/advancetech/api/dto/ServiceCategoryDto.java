package com.advancetech.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Service Category Data Transfer Object
 */
@Data
public class ServiceCategoryDto {
    private Long id;
    private String nameEn;
    private String nameAr;
    private String descriptionEn;
    private String descriptionAr;
    private String icon;
    private String imageUrl;
    private Integer displayOrder;
    private Boolean active;
    private Boolean featured;
    private Long serviceCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
