package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.QuoteRequest;
import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.QuoteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for QuoteRequest entity
 */
@Repository
public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
    
    /**
     * Find quotes by user
     */
    List<QuoteRequest> findByUser(User user);
    
    /**
     * Find quotes by user with pagination
     */
    Page<QuoteRequest> findByUser(User user, Pageable pageable);
    
    /**
     * Find quotes by status
     */
    List<QuoteRequest> findByStatus(QuoteStatus status);
    
    /**
     * Find quotes by user and status
     */
    List<QuoteRequest> findByUserAndStatus(User user, QuoteStatus status);
    
    /**
     * Find quotes by service
     */
    List<QuoteRequest> findByServiceId(Long serviceId);
    
    /**
     * Find quotes by budget range
     */
    @Query("SELECT q FROM QuoteRequest q WHERE " +
           "(:minBudget IS NULL OR q.budgetRangeMax >= :minBudget) AND " +
           "(:maxBudget IS NULL OR q.budgetRangeMin <= :maxBudget)")
    List<QuoteRequest> findByBudgetRange(@Param("minBudget") BigDecimal minBudget, 
                                        @Param("maxBudget") BigDecimal maxBudget);
    
    /**
     * Find quotes by urgency
     */
    List<QuoteRequest> findByUrgency(String urgency);
    
    /**
     * Find quotes by date range
     */
    @Query("SELECT q FROM QuoteRequest q WHERE q.createdAt BETWEEN :startDate AND :endDate")
    List<QuoteRequest> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, 
                                             @Param("endDate") LocalDateTime endDate);
    
    /**
     * Find quotes with filters
     */
    @Query("SELECT q FROM QuoteRequest q WHERE " +
           "(:userId IS NULL OR q.user.id = :userId) AND " +
           "(:serviceId IS NULL OR q.service.id = :serviceId) AND " +
           "(:status IS NULL OR q.status = :status) AND " +
           "(:urgency IS NULL OR q.urgency = :urgency)")
    Page<QuoteRequest> findQuotesWithFilters(@Param("userId") Long userId,
                                            @Param("serviceId") Long serviceId,
                                            @Param("status") QuoteStatus status,
                                            @Param("urgency") String urgency,
                                            Pageable pageable);
    
    /**
     * Count quotes by status
     */
    long countByStatus(QuoteStatus status);
    
    /**
     * Count quotes by user
     */
    long countByUser(User user);
    
    /**
     * Find high priority quotes
     */
    @Query("SELECT q FROM QuoteRequest q WHERE q.priorityScore >= :minPriority ORDER BY q.priorityScore DESC")
    List<QuoteRequest> findHighPriorityQuotes(@Param("minPriority") Integer minPriority);
    
    /**
     * Find quotes pending review
     */
    @Query("SELECT q FROM QuoteRequest q WHERE q.status = 'PENDING' ORDER BY q.createdAt ASC")
    List<QuoteRequest> findPendingQuotes();
    
    /**
     * Find quotes by assigned team
     */
    List<QuoteRequest> findByAssignedTeam(String assignedTeam);
}
