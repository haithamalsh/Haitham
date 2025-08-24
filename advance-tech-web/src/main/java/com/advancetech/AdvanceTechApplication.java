package com.advancetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main Spring Boot application class
 */
@SpringBootApplication
@EnableJpaAuditing
public class AdvanceTechApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AdvanceTechApplication.class, args);
    }
}
