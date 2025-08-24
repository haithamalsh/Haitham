package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.UserRole;
import com.advancetech.domain.enums.VerificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for User entity
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by username
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find user by email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find user by username or email
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Find users by role
     */
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r = :role")
    List<User> findByRole(@Param("role") UserRole role);
    
    /**
     * Find users by verification status
     */
    List<User> findByVerificationStatus(VerificationStatus status);
    
    /**
     * Find enabled users
     */
    List<User> findByEnabledTrue();
    
    /**
     * Find users by last login date range
     */
    @Query("SELECT u FROM User u WHERE u.lastLogin BETWEEN :startDate AND :endDate")
    List<User> findByLastLoginBetween(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
    
    /**
     * Find users with pagination and search
     */
    @Query("SELECT u FROM User u WHERE " +
           "(:username IS NULL OR u.username LIKE %:username%) AND " +
           "(:email IS NULL OR u.email LIKE %:email%) AND " +
           "(:enabled IS NULL OR u.enabled = :enabled) AND " +
           "(:verificationStatus IS NULL OR u.verificationStatus = :verificationStatus)")
    Page<User> findUsersWithFilters(@Param("username") String username,
                                   @Param("email") String email,
                                   @Param("enabled") Boolean enabled,
                                   @Param("verificationStatus") VerificationStatus verificationStatus,
                                   Pageable pageable);
    
    /**
     * Count users by role
     */
    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r = :role")
    long countByRole(@Param("role") UserRole role);
    
    /**
     * Find users who haven't logged in for a while
     */
    @Query("SELECT u FROM User u WHERE u.lastLogin < :date OR u.lastLogin IS NULL")
    List<User> findInactiveUsers(@Param("date") LocalDateTime date);
}
