package com.advancetech.persistence.repository;

import com.advancetech.domain.entity.Appointment;
import com.advancetech.domain.entity.User;
import com.advancetech.domain.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Appointment entity
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    /**
     * Find appointments by user
     */
    List<Appointment> findByUser(User user);
    
    /**
     * Find appointments by user with pagination
     */
    Page<Appointment> findByUser(User user, Pageable pageable);
    
    /**
     * Find appointments by status
     */
    List<Appointment> findByStatus(AppointmentStatus status);
    
    /**
     * Find appointments by user and status
     */
    List<Appointment> findByUserAndStatus(User user, AppointmentStatus status);
    
    /**
     * Find appointments by service
     */
    List<Appointment> findByServiceId(Long serviceId);
    
    /**
     * Find appointments by date
     */
    List<Appointment> findByAppointmentDate(LocalDate date);
    
    /**
     * Find appointments by date range
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findByDateRange(@Param("startDate") LocalDate startDate, 
                                     @Param("endDate") LocalDate endDate);
    
    /**
     * Find upcoming appointments
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate >= :date ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Appointment> findUpcomingAppointments(@Param("date") LocalDate date);
    
    /**
     * Find past appointments
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate < :date ORDER BY a.appointmentDate DESC")
    List<Appointment> findPastAppointments(@Param("date") LocalDate date);
    
    /**
     * Find appointments with filters
     */
    @Query("SELECT a FROM Appointment a WHERE " +
           "(:userId IS NULL OR a.user.id = :userId) AND " +
           "(:serviceId IS NULL OR a.service.id = :serviceId) AND " +
           "(:status IS NULL OR a.status = :status) AND " +
           "(:startDate IS NULL OR a.appointmentDate >= :startDate) AND " +
           "(:endDate IS NULL OR a.appointmentDate <= :endDate)")
    Page<Appointment> findAppointmentsWithFilters(@Param("userId") Long userId,
                                                  @Param("serviceId") Long serviceId,
                                                  @Param("status") AppointmentStatus status,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  Pageable pageable);
    
    /**
     * Count appointments by status
     */
    long countByStatus(AppointmentStatus status);
    
    /**
     * Count appointments by user
     */
    long countByUser(User user);
    
    /**
     * Find appointments needing reminder
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.reminderSent = false")
    List<Appointment> findAppointmentsNeedingReminder(@Param("date") LocalDate date);
    
    /**
     * Find confirmed appointments for today
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.status = 'CONFIRMED'")
    List<Appointment> findTodaysConfirmedAppointments(@Param("date") LocalDate date);
    
    /**
     * Find conflicting appointments
     */
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND " +
           "a.appointmentTime = :time AND a.status IN ('SCHEDULED', 'CONFIRMED')")
    List<Appointment> findConflictingAppointments(@Param("date") LocalDate date, 
                                                 @Param("time") java.time.LocalTime time);
}
