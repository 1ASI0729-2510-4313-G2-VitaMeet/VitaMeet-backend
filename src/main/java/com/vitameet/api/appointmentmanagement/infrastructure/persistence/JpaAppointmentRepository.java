package com.vitameet.api.appointmentmanagement.infrastructure.persistence;

import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface JpaAppointmentRepository extends JpaRepository<Appointment, Long> {
    
    List<Appointment> findByPatientId(Long patientId);
    
    List<Appointment> findByDoctorId(Long doctorId);
    
    List<Appointment> findByAppointmentDate(LocalDate date);
    
    List<Appointment> findByStatus(Appointment.AppointmentStatus status);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId AND a.appointmentDate = :date ORDER BY a.appointmentTime")
    List<Appointment> findByDoctorIdAndAppointmentDateOrderByAppointmentTime(
        @Param("doctorId") Long doctorId, 
        @Param("date") LocalDate date);
    
    @Query("SELECT a FROM Appointment a WHERE a.patientId = :patientId ORDER BY a.appointmentDate DESC, a.appointmentTime DESC")
    List<Appointment> findByPatientIdOrderByAppointmentDateDesc(@Param("patientId") Long patientId);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId AND a.appointmentDate = :date AND a.appointmentTime = :time")
    List<Appointment> findByDoctorIdAndAppointmentDateAndAppointmentTime(
        @Param("doctorId") Long doctorId, 
        @Param("date") LocalDate date, 
        @Param("time") LocalTime time);
}
