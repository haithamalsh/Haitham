package com.advancetech.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service entity
 */
@Entity
@Table(name = "services", indexes = {
    @Index(name = "idx_services_name_en", columnList = "name_en"),
    @Index(name = "idx_services_name_ar", columnList = "name_ar"),
    @Index(name = "idx_services_category", columnList = "category_id"),
    @Index(name = "idx_services_popular", columnList = "popular")
})
@Getter
@Setter
public class Service extends BaseEntity {
    
    @NotNull(message = "Category is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;
    
    @NotBlank(message = "English name is required")
    @Size(max = 200, message = "English name must not exceed 200 characters")
    @Column(name = "name_en", nullable = false, length = 200)
    private String nameEn;
    
    @NotBlank(message = "Arabic name is required")
    @Size(max = 200, message = "Arabic name must not exceed 200 characters")
    @Column(name = "name_ar", nullable = false, length = 200)
    private String nameAr;
    
    @Size(max = 1000, message = "English description must not exceed 1000 characters")
    @Column(name = "description_en", length = 1000)
    private String descriptionEn;
    
    @Size(max = 1000, message = "Arabic description must not exceed 1000 characters")
    @Column(name = "description_ar", length = 1000)
    private String descriptionAr;
    
    @Column(name = "price_range_min", precision = 10, scale = 2)
    private BigDecimal priceRangeMin;
    
    @Column(name = "price_range_max", precision = 10, scale = 2)
    private BigDecimal priceRangeMax;
    
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    
    @Column(name = "features", columnDefinition = "JSON")
    private String features; // JSON string of features
    
    @Column(name = "images", columnDefinition = "JSON")
    private String images; // JSON array of image URLs
    
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    
    @Column(name = "popular", nullable = false)
    private Boolean popular = false;
    
    @Column(name = "featured", nullable = false)
    private Boolean featured = false;
    
    @Column(name = "rating_average", precision = 3, scale = 2)
    private BigDecimal ratingAverage = BigDecimal.ZERO;
    
    @Column(name = "rating_count")
    private Integer ratingCount = 0;
    
    // Helper methods
    public boolean isInPriceRange(BigDecimal amount) {
        if (priceRangeMin == null && priceRangeMax == null) {
            return true;
        }
        if (priceRangeMin != null && amount.compareTo(priceRangeMin) < 0) {
            return false;
        }
        if (priceRangeMax != null && amount.compareTo(priceRangeMax) > 0) {
            return false;
        }
        return true;
    }
}
