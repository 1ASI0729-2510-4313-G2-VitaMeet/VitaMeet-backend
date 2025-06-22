package upc.edu.pe.vitameet.scheduling.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.vitameet.scheduling.application.internal.queryservices.ScheduleQueryServiceImpl;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetAllSchedulesQuery;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetScheduleByIdQuery;
import upc.edu.pe.vitameet.scheduling.interfaces.rest.resource.ScheduleResource;
import upc.edu.pe.vitameet.scheduling.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedules", description = "API for managing doctor schedules")
public class ScheduleController {

    private final ScheduleQueryServiceImpl scheduleQueryService;

    @GetMapping
    public ResponseEntity<List<ScheduleResource>> getAll() {
        var query = new GetAllSchedulesQuery();
        var schedules = scheduleQueryService.handle(query)
                .stream()
                .map(ScheduleResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResource> getById(@PathVariable Long id) {
        var query = new GetScheduleByIdQuery(id);
        return scheduleQueryService.handle(query)
                .map(ScheduleResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
