package com.vitameet.api.appointmentmanagement.application.services;

import com.vitameet.api.appointmentmanagement.application.commands.CreateAppointmentCommand;
import com.vitameet.api.appointmentmanagement.application.commands.UpdateAppointmentCommand;
import com.vitameet.api.appointmentmanagement.application.commands.CancelAppointmentCommand;
import com.vitameet.api.appointmentmanagement.domain.model.Appointment;
import com.vitameet.api.appointmentmanagement.domain.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentCommandService {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentCommandService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    
    public Appointment createAppointment(CreateAppointmentCommand command) {
        // Validate that the appointment slot is available
        var existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateAndAppointmentTime(
            command.getDoctorId(), command.getAppointmentDate(), command.getAppointmentTime());
        
        if (!existingAppointments.isEmpty()) {
            throw new IllegalStateException("El horario ya está ocupado para este médico");
        }
        
        var appointment = new Appointment(
            command.getPatientId(),
            command.getDoctorId(),
            command.getAppointmentDate(),
            command.getAppointmentTime(),
            command.getReason()
        );
        
        return appointmentRepository.save(appointment);
    }
    
    public Appointment updateAppointment(UpdateAppointmentCommand command) {
        var appointment = appointmentRepository.findById(command.getAppointmentId())
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        
        // If changing date/time, validate availability
        if (!appointment.getAppointmentDate().equals(command.getAppointmentDate()) ||
            !appointment.getAppointmentTime().equals(command.getAppointmentTime())) {
            
            var existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateAndAppointmentTime(
                appointment.getDoctorId(), command.getAppointmentDate(), command.getAppointmentTime());
            
            if (existingAppointments.stream().anyMatch(a -> !a.getId().equals(appointment.getId()))) {
                throw new IllegalStateException("El horario ya está ocupado para este médico");
            }
            
            appointment.reschedule(command.getAppointmentDate(), command.getAppointmentTime());
        }
        
        return appointmentRepository.save(appointment);
    }
    
    public void cancelAppointment(CancelAppointmentCommand command) {
        var appointment = appointmentRepository.findById(command.getAppointmentId())
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        
        appointment.cancel();
        appointmentRepository.save(appointment);
    }
    
    public void completeAppointment(Long appointmentId) {
        var appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        
        appointment.complete();
        appointmentRepository.save(appointment);
    }
    
    public void deleteAppointment(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new IllegalArgumentException("Cita no encontrada");
        }
        appointmentRepository.deleteById(appointmentId);
    }
}
