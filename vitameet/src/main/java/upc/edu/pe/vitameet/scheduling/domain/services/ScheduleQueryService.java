package upc.edu.pe.vitameet.scheduling.domain.services;

import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetAllSchedulesQuery;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetScheduleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {
    Optional<Schedule> handle(GetScheduleByIdQuery query);
    List<Schedule> handle(GetAllSchedulesQuery query);
}
