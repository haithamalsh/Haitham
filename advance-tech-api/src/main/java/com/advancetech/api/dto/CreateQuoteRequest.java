package com.advancetech.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request DTO for creating a new quote request
 */
@Data
public class CreateQuoteRequest {
    
    @NotNull(message = "Service ID is required")
    private Long serviceId;
    
    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;
    
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;
    
    private BigDecimal budgetRangeMin;
    private BigDecimal budgetRangeMax;
    
    @Size(max = 100, message = "Urgency must not exceed 100 characters")
    private String urgency;
}
