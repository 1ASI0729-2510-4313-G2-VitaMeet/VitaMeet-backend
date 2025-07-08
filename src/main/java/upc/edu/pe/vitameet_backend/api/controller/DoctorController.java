package upc.edu.pe.vitameet_backend.api.controller;

import upc.edu.pe.vitameet_backend.api.model.Doctor;
import upc.edu.pe.vitameet_backend.api.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
