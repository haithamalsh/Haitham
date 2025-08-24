package com.advancetech.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Service category entity
 */
@Entity
@Table(name = "service_categories", indexes = {
    @Index(name = "idx_categories_name_en", columnList = "name_en"),
    @Index(name = "idx_categories_name_ar", columnList = "name_ar")
})
@Getter
@Setter
public class ServiceCategory extends BaseEntity {
    
    @NotBlank(message = "English name is required")
    @Size(max = 100, message = "English name must not exceed 100 characters")
    @Column(name = "name_en", nullable = false, length = 100)
    private String nameEn;
    
    @NotBlank(message = "Arabic name is required")
    @Size(max = 100, message = "Arabic name must not exceed 100 characters")
    @Column(name = "name_ar", nullable = false, length = 100)
    private String nameAr;
    
    @Size(max = 500, message = "English description must not exceed 500 characters")
    @Column(name = "description_en", length = 500)
    private String descriptionEn;
    
    @Size(max = 500, message = "Arabic description must not exceed 500 characters")
    @Column(name = "description_ar", length = 500)
    private String descriptionAr;
    
    @Size(max = 100, message = "Icon must not exceed 100 characters")
    @Column(name = "icon", length = 100)
    private String icon;
    
    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Column(name = "image_url", length = 500)
    private String imageUrl;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    
    @Column(name = "featured", nullable = false)
    private Boolean featured = false;
}
