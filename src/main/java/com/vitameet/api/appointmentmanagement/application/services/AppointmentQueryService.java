package com.vitameet.api.appointmentmanagement.application.services;

import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentByIdQuery;
import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentsByPatientIdQuery;
import com.vitameet.api.appointmentmanagement.application.queries.GetAppointmentsByDoctorIdQuery;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import com.vitameet.api.appointmentmanagement.domain.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppointmentQueryService {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentQueryService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    
    public Optional<Appointment> getAppointmentById(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.getAppointmentId());
    }
    
    public List<Appointment> getAppointmentsByPatientId(GetAppointmentsByPatientIdQuery query) {
        return appointmentRepository.findByPatientIdOrderByAppointmentDateDesc(query.getPatientId());
    }
    
    public List<Appointment> getAppointmentsByDoctorId(GetAppointmentsByDoctorIdQuery query) {
        return appointmentRepository.findByDoctorId(query.getDoctorId());
    }
    
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }
    
    public List<Appointment> getAppointmentsByStatus(Appointment.AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }
    
    public List<Appointment> getDoctorAppointmentsByDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateOrderByAppointmentTime(doctorId, date);
    }
}
