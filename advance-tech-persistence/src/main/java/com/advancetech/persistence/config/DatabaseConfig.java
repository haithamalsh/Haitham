package com.advancetech.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database configuration for JPA and repositories
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.advancetech.persistence.repository")
@EntityScan(basePackages = "com.advancetech.domain.entity")
@EnableTransactionManagement
public class DatabaseConfig {
    // Configuration is handled by Spring Boot auto-configuration
    // Additional customizations can be added here if needed
}
