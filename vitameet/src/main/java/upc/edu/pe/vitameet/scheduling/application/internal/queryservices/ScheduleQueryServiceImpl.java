package upc.edu.pe.vitameet.scheduling.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetAllSchedulesQuery;
import upc.edu.pe.vitameet.scheduling.domain.model.queries.GetScheduleByIdQuery;
import upc.edu.pe.vitameet.scheduling.domain.services.ScheduleQueryService;
import upc.edu.pe.vitameet.scheduling.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository repository;

    public ScheduleQueryServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        return repository.findById(query.scheduleId());
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesQuery query) {
        return repository.findAll();
    }
}
