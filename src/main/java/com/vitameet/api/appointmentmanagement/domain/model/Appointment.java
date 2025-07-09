package com.vitameet.api.appointmentmanagement.domain.model;

import com.vitameet.api.shared.domain.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Appointment extends BaseEntity {

    @Column(name = "paciente_id", nullable = false)
    private Long patientId;

    @Column(name = "medico_id", nullable = false)
    private Long doctorId;

    @Column(name = "fecha", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "hora", nullable = false)
    private LocalTime appointmentTime;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "motivo", length = 255)
    private String reason;

    public enum AppointmentStatus {
        PROGRAMADA, COMPLETADA, CANCELADA, REPROGRAMADA
    }

    // Constructors
    protected Appointment() {
    }

    public Appointment(Long patientId, Long doctorId, LocalDate appointmentDate,
            LocalTime appointmentTime, String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = AppointmentStatus.PROGRAMADA;
    }

    // Business methods
    public void complete() {
        if (this.status != AppointmentStatus.PROGRAMADA) {
            throw new IllegalStateException("Solo se pueden completar citas programadas");
        }
        this.status = AppointmentStatus.COMPLETADA;
    }

    public void cancel() {
        if (this.status == AppointmentStatus.COMPLETADA) {
            throw new IllegalStateException("No se puede cancelar una cita completada");
        }
        this.status = AppointmentStatus.CANCELADA;
    }

    public void reschedule(LocalDate newDate, LocalTime newTime) {
        if (this.status != AppointmentStatus.PROGRAMADA) {
            throw new IllegalStateException("Solo se pueden reprogramar citas programadas");
        }
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = AppointmentStatus.REPROGRAMADA;
    }

    public LocalDateTime getFullDateTime() {
        return LocalDateTime.of(appointmentDate, appointmentTime);
    }

    public boolean isInThePast() {
        return getFullDateTime().isBefore(LocalDateTime.now());
    }

    // Getters
    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
