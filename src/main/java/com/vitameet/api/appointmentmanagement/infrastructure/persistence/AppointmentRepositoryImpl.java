package com.vitameet.api.appointmentmanagement.infrastructure.persistence;

import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import com.vitameet.api.appointmentmanagement.domain.repositories.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    
    private final JpaAppointmentRepository jpaAppointmentRepository;
    
    public AppointmentRepositoryImpl(JpaAppointmentRepository jpaAppointmentRepository) {
        this.jpaAppointmentRepository = jpaAppointmentRepository;
    }
    
    @Override
    public List<Appointment> findAll() {
        return jpaAppointmentRepository.findAll();
    }
    
    @Override
    public Optional<Appointment> findById(Long id) {
        return jpaAppointmentRepository.findById(id);
    }
    
    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return jpaAppointmentRepository.findByPatientId(patientId);
    }
    
    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return jpaAppointmentRepository.findByDoctorId(doctorId);
    }
    
    @Override
    public List<Appointment> findByAppointmentDate(LocalDate date) {
        return jpaAppointmentRepository.findByAppointmentDate(date);
    }
    
    @Override
    public List<Appointment> findByStatus(Appointment.AppointmentStatus status) {
        return jpaAppointmentRepository.findByStatus(status);
    }
    
    @Override
    public List<Appointment> findByDoctorIdAndAppointmentDateOrderByAppointmentTime(Long doctorId, LocalDate date) {
        return jpaAppointmentRepository.findByDoctorIdAndAppointmentDateOrderByAppointmentTime(doctorId, date);
    }
    
    @Override
    public List<Appointment> findByPatientIdOrderByAppointmentDateDesc(Long patientId) {
        return jpaAppointmentRepository.findByPatientIdOrderByAppointmentDateDesc(patientId);
    }
    
    @Override
    public List<Appointment> findByDoctorIdAndAppointmentDateAndAppointmentTime(Long doctorId, LocalDate date, LocalTime time) {
        return jpaAppointmentRepository.findByDoctorIdAndAppointmentDateAndAppointmentTime(doctorId, date, time);
    }
    
    @Override
    public Appointment save(Appointment appointment) {
        return jpaAppointmentRepository.save(appointment);
    }
    
    @Override
    public void delete(Appointment appointment) {
        jpaAppointmentRepository.delete(appointment);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaAppointmentRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaAppointmentRepository.existsById(id);
    }
}
