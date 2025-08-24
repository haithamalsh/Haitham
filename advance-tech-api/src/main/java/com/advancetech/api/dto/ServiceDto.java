package com.advancetech.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service Data Transfer Object
 */
@Data
public class ServiceDto {
    private Long id;
    private Long categoryId;
    private String categoryNameEn;
    private String categoryNameAr;
    private String nameEn;
    private String nameAr;
    private String descriptionEn;
    private String descriptionAr;
    private BigDecimal priceRangeMin;
    private BigDecimal priceRangeMax;
    private Integer durationMinutes;
    private String features;
    private String images;
    private Boolean active;
    private Boolean popular;
    private Boolean featured;
    private BigDecimal ratingAverage;
    private Integer ratingCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
