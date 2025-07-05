package com.vitameet.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitameet.api.model.Doctor;
import com.vitameet.api.repository.DoctorRepository;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/")
    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> obtenerDoctor(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .map(doctor -> ResponseEntity.ok(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Doctor crearDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> editarDoctor(@PathVariable Long id, @RequestBody Doctor doctorActualizado) {
        return doctorRepository.findById(id)
                .map(doctorExistente -> {
                    doctorExistente.setNombre(doctorActualizado.getNombre());
                    doctorExistente.setEspecialidad(doctorActualizado.getEspecialidad());
                    Doctor doctorGuardado = doctorRepository.save(doctorExistente);
                    return ResponseEntity.ok(doctorGuardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarDoctor(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctorRepository.delete(doctor);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Doctor eliminado exitosamente");
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
