package upc.edu.pe.vitameet.appointment.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet.appointment.application.internal.commandservices.AppointmentCommandServiceImpl;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CancelAppointmentCommand;
import upc.edu.pe.vitameet.appointment.domain.model.commands.CreateAppointmentCommand;
import upc.edu.pe.vitameet.appointment.interfaces.rest.resource.AppointmentResource;
import upc.edu.pe.vitameet.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "API for managing appointments")
public class AppointmentController {

    private final AppointmentCommandServiceImpl appointmentCommandService;

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentCommand command) {
        var appointment = appointmentCommandService.handle(command);
        var resource = AppointmentResourceFromEntityAssembler.toResource(appointment);
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        appointmentCommandService.handle(new CancelAppointmentCommand(id));
        return ResponseEntity.ok("Appointment canceled successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentCommandService.delete(id);
        return ResponseEntity.ok("Appointment deleted successfully.");
    }
}
