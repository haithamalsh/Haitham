package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.Service;
import com.advancetech.domain.entity.ServiceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for Service entity
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
    /**
     * Find services by category
     */
    List<Service> findByCategory(ServiceCategory category);
    
    /**
     * Find active services
     */
    List<Service> findByActiveTrue();
    
    /**
     * Find popular services
     */
    List<Service> findByPopularTrue();
    
    /**
     * Find featured services
     */
    List<Service> findByFeaturedTrue();
    
    /**
     * Find services by category and active status
     */
    List<Service> findByCategoryAndActiveTrue(ServiceCategory category);
    
    /**
     * Find services with price range
     */
    @Query("SELECT s FROM Service s WHERE " +
           "(:minPrice IS NULL OR s.priceRangeMax >= :minPrice) AND " +
           "(:maxPrice IS NULL OR s.priceRangeMin <= :maxPrice) AND " +
           "s.active = true")
    List<Service> findByPriceRange(@Param("minPrice") BigDecimal minPrice, 
                                  @Param("maxPrice") BigDecimal maxPrice);
    
    /**
     * Find services by search term
     */
    @Query("SELECT s FROM Service s WHERE " +
           "s.active = true AND " +
           "(LOWER(s.nameEn) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(s.nameAr) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(s.descriptionEn) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(s.descriptionAr) LIKE LOWER(%:searchTerm%))")
    Page<Service> searchServices(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    /**
     * Find services with filters
     */
    @Query("SELECT s FROM Service s WHERE " +
           "(:categoryId IS NULL OR s.category.id = :categoryId) AND " +
           "(:active IS NULL OR s.active = :active) AND " +
           "(:popular IS NULL OR s.popular = :popular) AND " +
           "(:featured IS NULL OR s.featured = :featured)")
    Page<Service> findServicesWithFilters(@Param("categoryId") Long categoryId,
                                         @Param("active") Boolean active,
                                         @Param("popular") Boolean popular,
                                         @Param("featured") Boolean featured,
                                         Pageable pageable);
    
    /**
     * Find top rated services
     */
    @Query("SELECT s FROM Service s WHERE s.active = true ORDER BY s.ratingAverage DESC, s.ratingCount DESC")
    List<Service> findTopRatedServices(Pageable pageable);
    
    /**
     * Count services by category
     */
    long countByCategory(ServiceCategory category);
    
    /**
     * Count active services
     */
    long countByActiveTrue();
    
    /**
     * Find services by duration range
     */
    @Query("SELECT s FROM Service s WHERE " +
           "(:minDuration IS NULL OR s.durationMinutes >= :minDuration) AND " +
           "(:maxDuration IS NULL OR s.durationMinutes <= :maxDuration) AND " +
           "s.active = true")
    List<Service> findByDurationRange(@Param("minDuration") Integer minDuration, 
                                     @Param("maxDuration") Integer maxDuration);
}
