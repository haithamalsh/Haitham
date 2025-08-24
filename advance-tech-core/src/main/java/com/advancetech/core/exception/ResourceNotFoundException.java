package com.advancetech.core.exception;

/**
 * Exception thrown when a requested resource is not found
 */
public class ResourceNotFoundException extends BusinessException {
    
    public ResourceNotFoundException(String message) {
        super(message, "RESOURCE_NOT_FOUND");
    }
    
    public ResourceNotFoundException(String resourceType, String identifier) {
        super(String.format("%s with identifier %s not found", resourceType, identifier), 
              "RESOURCE_NOT_FOUND");
    }
}
