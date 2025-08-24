package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.Review;
import com.advancetech.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Review entity
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    /**
     * Find reviews by user
     */
    List<Review> findByUser(User user);
    
    /**
     * Find reviews by service
     */
    List<Review> findByServiceId(Long serviceId);
    
    /**
     * Find reviews by project
     */
    List<Review> findByProjectId(Long projectId);
    
    /**
     * Find approved reviews
     */
    List<Review> findByApprovedTrue();
    
    /**
     * Find pending reviews
     */
    List<Review> findByApprovedFalse();
    
    /**
     * Find reviews by rating
     */
    List<Review> findByRating(Integer rating);
    
    /**
     * Find reviews by rating range
     */
    @Query("SELECT r FROM Review r WHERE r.rating BETWEEN :minRating AND :maxRating")
    List<Review> findByRatingRange(@Param("minRating") Integer minRating, 
                                  @Param("maxRating") Integer maxRating);
    
    /**
     * Find approved reviews for service
     */
    @Query("SELECT r FROM Review r WHERE r.service.id = :serviceId AND r.approved = true ORDER BY r.createdAt DESC")
    List<Review> findApprovedReviewsByService(@Param("serviceId") Long serviceId);
    
    /**
     * Find approved reviews for project
     */
    @Query("SELECT r FROM Review r WHERE r.project.id = :projectId AND r.approved = true ORDER BY r.createdAt DESC")
    List<Review> findApprovedReviewsByProject(@Param("projectId") Long projectId);
    
    /**
     * Find reviews with filters
     */
    @Query("SELECT r FROM Review r WHERE " +
           "(:userId IS NULL OR r.user.id = :userId) AND " +
           "(:serviceId IS NULL OR r.service.id = :serviceId) AND " +
           "(:projectId IS NULL OR r.project.id = :projectId) AND " +
           "(:rating IS NULL OR r.rating = :rating) AND " +
           "(:approved IS NULL OR r.approved = :approved)")
    Page<Review> findReviewsWithFilters(@Param("userId") Long userId,
                                       @Param("serviceId") Long serviceId,
                                       @Param("projectId") Long projectId,
                                       @Param("rating") Integer rating,
                                       @Param("approved") Boolean approved,
                                       Pageable pageable);
    
    /**
     * Count reviews by service
     */
    long countByServiceId(Long serviceId);
    
    /**
     * Count reviews by project
     */
    long countByProjectId(Long projectId);
    
    /**
     * Count approved reviews by service
     */
    @Query("SELECT COUNT(r) FROM Review r WHERE r.service.id = :serviceId AND r.approved = true")
    long countApprovedReviewsByService(@Param("serviceId") Long serviceId);
    
    /**
     * Calculate average rating for service
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.service.id = :serviceId AND r.approved = true")
    Double getAverageRatingByService(@Param("serviceId") Long serviceId);
    
    /**
     * Calculate average rating for project
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.project.id = :projectId AND r.approved = true")
    Double getAverageRatingByProject(@Param("projectId") Long projectId);
    
    /**
     * Find positive reviews (rating >= 4)
     */
    @Query("SELECT r FROM Review r WHERE r.rating >= 4 AND r.approved = true")
    List<Review> findPositiveReviews();
    
    /**
     * Find negative reviews (rating <= 2)
     */
    @Query("SELECT r FROM Review r WHERE r.rating <= 2 AND r.approved = true")
    List<Review> findNegativeReviews();
    
    /**
     * Find reported reviews
     */
    List<Review> findByReportedTrue();
    
    /**
     * Find most helpful reviews
     */
    @Query("SELECT r FROM Review r WHERE r.approved = true ORDER BY r.helpfulCount DESC")
    List<Review> findMostHelpfulReviews(Pageable pageable);
    
    /**
     * Find recent reviews
     */
    @Query("SELECT r FROM Review r WHERE r.approved = true ORDER BY r.createdAt DESC")
    List<Review> findRecentReviews(Pageable pageable);
}
