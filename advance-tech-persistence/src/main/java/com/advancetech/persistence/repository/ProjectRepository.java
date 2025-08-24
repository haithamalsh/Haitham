package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.Project;
import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.ProjectStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Project entity
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    /**
     * Find projects by user
     */
    List<Project> findByUser(User user);
    
    /**
     * Find projects by user with pagination
     */
    Page<Project> findByUser(User user, Pageable pageable);
    
    /**
     * Find projects by status
     */
    List<Project> findByStatus(ProjectStatus status);
    
    /**
     * Find projects by user and status
     */
    List<Project> findByUserAndStatus(User user, ProjectStatus status);
    
    /**
     * Find projects by service
     */
    List<Project> findByServiceId(Long serviceId);
    
    /**
     * Find active projects
     */
    @Query("SELECT p FROM Project p WHERE p.status IN ('PLANNING', 'IN_PROGRESS')")
    List<Project> findActiveProjects();
    
    /**
     * Find completed projects
     */
    List<Project> findByStatusOrderByCreatedAtDesc(ProjectStatus status);
    
    /**
     * Find delayed projects
     */
    @Query("SELECT p FROM Project p WHERE (p.status = 'DELAYED' OR " +
           "(p.endDate < :currentDate AND p.status != 'COMPLETED')) " +
           "ORDER BY p.endDate ASC")
    List<Project> findDelayedProjects(@Param("currentDate") LocalDate currentDate);
    
    /**
     * Find projects with budget range
     */
    @Query("SELECT p FROM Project p WHERE " +
           "(:minBudget IS NULL OR p.budget >= :minBudget) AND " +
           "(:maxBudget IS NULL OR p.budget <= :maxBudget)")
    List<Project> findByBudgetRange(@Param("minBudget") BigDecimal minBudget, 
                                   @Param("maxBudget") BigDecimal maxBudget);
    
    /**
     * Find projects by date range
     */
    @Query("SELECT p FROM Project p WHERE " +
           "(:startDate IS NULL OR p.startDate >= :startDate) AND " +
           "(:endDate IS NULL OR p.endDate <= :endDate)")
    List<Project> findByDateRange(@Param("startDate") LocalDate startDate, 
                                 @Param("endDate") LocalDate endDate);
    
    /**
     * Find projects with filters
     */
    @Query("SELECT p FROM Project p WHERE " +
           "(:userId IS NULL OR p.user.id = :userId) AND " +
           "(:serviceId IS NULL OR p.service.id = :serviceId) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:minProgress IS NULL OR p.progressPercentage >= :minProgress) AND " +
           "(:maxProgress IS NULL OR p.progressPercentage <= :maxProgress)")
    Page<Project> findProjectsWithFilters(@Param("userId") Long userId,
                                         @Param("serviceId") Long serviceId,
                                         @Param("status") ProjectStatus status,
                                         @Param("minProgress") Integer minProgress,
                                         @Param("maxProgress") Integer maxProgress,
                                         Pageable pageable);
    
    /**
     * Count projects by status
     */
    long countByStatus(ProjectStatus status);
    
    /**
     * Count projects by user
     */
    long countByUser(User user);
    
    /**
     * Find high priority projects
     */
    @Query("SELECT p FROM Project p WHERE p.priorityScore >= :minPriority ORDER BY p.priorityScore DESC")
    List<Project> findHighPriorityProjects(@Param("minPriority") Integer minPriority);
    
    /**
     * Find projects by progress range
     */
    @Query("SELECT p FROM Project p WHERE p.progressPercentage BETWEEN :minProgress AND :maxProgress")
    List<Project> findByProgressRange(@Param("minProgress") Integer minProgress, 
                                     @Param("maxProgress") Integer maxProgress);
    
    /**
     * Find projects ending soon
     */
    @Query("SELECT p FROM Project p WHERE p.endDate BETWEEN :startDate AND :endDate AND p.status != 'COMPLETED'")
    List<Project> findProjectsEndingSoon(@Param("startDate") LocalDate startDate, 
                                        @Param("endDate") LocalDate endDate);
    
    /**
     * Calculate average progress by status
     */
    @Query("SELECT AVG(p.progressPercentage) FROM Project p WHERE p.status = :status")
    Double getAverageProgressByStatus(@Param("status") ProjectStatus status);
    
    /**
     * Find overbudget projects
     */
    @Query("SELECT p FROM Project p WHERE p.actualCost > p.budget AND p.budget IS NOT NULL")
    List<Project> findOverbudgetProjects();
}
