package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for ServiceCategory entity
 */
@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    
    /**
     * Find active categories
     */
    List<ServiceCategory> findByActiveTrue();
    
    /**
     * Find featured categories
     */
    List<ServiceCategory> findByFeaturedTrue();
    
    /**
     * Find categories by display order
     */
    List<ServiceCategory> findByActiveTrueOrderByDisplayOrderAsc();
    
    /**
     * Find categories with services count
     */
    @Query("SELECT c, COUNT(s) as serviceCount FROM ServiceCategory c " +
           "LEFT JOIN c.services s WHERE c.active = true " +
           "GROUP BY c ORDER BY c.displayOrder ASC")
    List<Object[]> findCategoriesWithServiceCount();
    
    /**
     * Find category by name (English or Arabic)
     */
    @Query("SELECT c FROM ServiceCategory c WHERE " +
           "c.active = true AND " +
           "(LOWER(c.nameEn) = LOWER(:name) OR LOWER(c.nameAr) = LOWER(:name))")
    ServiceCategory findByNameIgnoreCase(@org.springframework.data.repository.query.Param("name") String name);
    
    /**
     * Search categories by term
     */
    @Query("SELECT c FROM ServiceCategory c WHERE " +
           "c.active = true AND " +
           "(LOWER(c.nameEn) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(c.nameAr) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(c.descriptionEn) LIKE LOWER(%:searchTerm%) OR " +
           "LOWER(c.descriptionAr) LIKE LOWER(%:searchTerm%))")
    List<ServiceCategory> searchCategories(@org.springframework.data.repository.query.Param("searchTerm") String searchTerm);
    
    /**
     * Count active categories
     */
    long countByActiveTrue();
}
