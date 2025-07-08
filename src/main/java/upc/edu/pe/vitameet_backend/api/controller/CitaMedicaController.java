package upc.edu.pe.vitameet_backend.api.controller;

import upc.edu.pe.vitameet_backend.api.model.CitaMedica;
import upc.edu.pe.vitameet_backend.api.model.Doctor;
import upc.edu.pe.vitameet_backend.api.model.Usuario;
import upc.edu.pe.vitameet_backend.api.repository.CitaMedicaRepository;
import upc.edu.pe.vitameet_backend.api.repository.DoctorRepository;
import upc.edu.pe.vitameet_backend.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
public class CitaMedicaController {
    private final CitaMedicaRepository citaMedicaRepository;
    private final UsuarioRepository usuarioRepository;
    private final DoctorRepository doctorRepository;

    public CitaMedicaController(CitaMedicaRepository citaMedicaRepository, 
                               UsuarioRepository usuarioRepository, 
                               DoctorRepository doctorRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/")
    public List<CitaMedica> listarCitas() {
        return citaMedicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> obtenerCita(@PathVariable Long id) {
        return citaMedicaRepository.findById(id)
                .map(cita -> ResponseEntity.ok(cita))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<?> crearCita(@RequestBody Map<String, Object> citaData) {
        try {
            Long usuarioId = Long.parseLong(citaData.get("usuarioId").toString());
            Long doctorId = Long.parseLong(citaData.get("doctorId").toString());
            String fecha = citaData.get("fecha").toString();
            String hora = citaData.get("hora").toString();

            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElse(null);
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElse(null);

            if (usuario == null || doctor == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Usuario o Doctor no encontrado");
                return ResponseEntity.badRequest().body(error);
            }

            CitaMedica cita = new CitaMedica();
            cita.setFecha(java.time.LocalDate.parse(fecha));
            cita.setHora(java.time.LocalTime.parse(hora));
            cita.setUsuario(usuario);
            cita.setDoctor(doctor);

            CitaMedica citaGuardada = citaMedicaRepository.save(cita);
            return ResponseEntity.ok(citaGuardada);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear la cita: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCita(@PathVariable Long id, @RequestBody Map<String, Object> citaData) {
        return citaMedicaRepository.findById(id)
                .map(citaExistente -> {
                    try {
                        if (citaData.containsKey("fecha")) {
                            citaExistente.setFecha(java.time.LocalDate.parse(citaData.get("fecha").toString()));
                        }
                        if (citaData.containsKey("hora")) {
                            citaExistente.setHora(java.time.LocalTime.parse(citaData.get("hora").toString()));
                        }
                        if (citaData.containsKey("usuarioId")) {
                            Long usuarioId = Long.parseLong(citaData.get("usuarioId").toString());
                            Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
                            if (usuario != null) {
                                citaExistente.setUsuario(usuario);
                            }
                        }
                        if (citaData.containsKey("doctorId")) {
                            Long doctorId = Long.parseLong(citaData.get("doctorId").toString());
                            Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                            if (doctor != null) {
                                citaExistente.setDoctor(doctor);
                            }
                        }

                        CitaMedica citaGuardada = citaMedicaRepository.save(citaExistente);
                        return ResponseEntity.ok(citaGuardada);
                    } catch (Exception e) {
                        Map<String, String> error = new HashMap<>();
                        error.put("error", "Error al actualizar la cita: " + e.getMessage());
                        return ResponseEntity.badRequest().body(error);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCita(@PathVariable Long id) {
        return citaMedicaRepository.findById(id)
                .map(cita -> {
                    citaMedicaRepository.delete(cita);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Cita eliminada exitosamente");
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CitaMedica>> obtenerCitasPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<CitaMedica> citas = citaMedicaRepository.findAll().stream()
                .filter(cita -> cita.getUsuario().getId().equals(usuarioId))
                .toList();
        
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<CitaMedica>> obtenerCitasPorDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<CitaMedica> citas = citaMedicaRepository.findAll().stream()
                .filter(cita -> cita.getDoctor().getId().equals(doctorId))
                .toList();
        
        return ResponseEntity.ok(citas);
    }
}
