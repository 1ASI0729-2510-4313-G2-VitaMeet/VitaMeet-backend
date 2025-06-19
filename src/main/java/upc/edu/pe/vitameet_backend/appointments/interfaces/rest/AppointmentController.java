package upc.edu.pe.vitameet_backend.appointments.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet_backend.appointments.application.internal.commandservices.BookAppointmentService;
import upc.edu.pe.vitameet_backend.appointments.application.internal.commandservices.CancelAppointmentService;
import upc.edu.pe.vitameet_backend.appointments.application.internal.queryservices.GetAppointmentsService;
import upc.edu.pe.vitameet_backend.appointments.domain.model.aggregates.Appointment;
import upc.edu.pe.vitameet_backend.appointments.interfaces.rest.resource.BookAppointmentResource;
import upc.edu.pe.vitameet_backend.appointments.interfaces.rest.transform.AppointmentResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final BookAppointmentService bookService;
    private final CancelAppointmentService cancelService;
    private final GetAppointmentsService queryService;

    public AppointmentController(BookAppointmentService bookService, CancelAppointmentService cancelService, GetAppointmentsService queryService) {
        this.bookService = bookService;
        this.cancelService = cancelService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<Appointment> book(@RequestBody BookAppointmentResource resource) {
        var command = AppointmentResourceAssembler.toCommand(resource);
        return ResponseEntity.ok(bookService.handle(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        cancelService.handle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(queryService.getByPatientId(patientId));
    }
}
