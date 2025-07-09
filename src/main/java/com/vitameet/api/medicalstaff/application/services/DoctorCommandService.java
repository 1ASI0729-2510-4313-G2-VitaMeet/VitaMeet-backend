package com.vitameet.api.medicalstaff.application.services;

import com.vitameet.api.medicalstaff.application.commands.CreateDoctorCommand;
import com.vitameet.api.medicalstaff.application.commands.UpdateDoctorCommand;
import com.vitameet.api.medicalstaff.domain.model.Doctor;
import com.vitameet.api.medicalstaff.domain.repositories.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorCommandService {
    
    private final DoctorRepository doctorRepository;

    public DoctorCommandService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor handle(CreateDoctorCommand command) {
        if (doctorRepository.existsByMedicalLicense(command.medicalLicense())) {
            throw new IllegalArgumentException("Ya existe un médico con ese número de colegiatura");
        }

        Doctor doctor = new Doctor(
            command.specialty(),
            command.medicalLicense(),
            command.officeLocation()
        );

        return doctorRepository.save(doctor);
    }

    public Doctor handle(UpdateDoctorCommand command) {
        Doctor doctor = doctorRepository.findById(command.doctorId())
            .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));

        doctor.updateProfessionalInfo(command.specialty(), command.officeLocation());
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));
        
        doctorRepository.delete(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
