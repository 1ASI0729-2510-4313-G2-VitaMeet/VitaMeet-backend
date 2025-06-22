package upc.edu.pe.vitameet.scheduling.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;
import upc.edu.pe.vitameet.scheduling.domain.model.commands.CreateScheduleCommand;
import upc.edu.pe.vitameet.scheduling.domain.services.ScheduleCommandService;
import upc.edu.pe.vitameet.scheduling.infrastructure.persistence.jpa.repositories.ScheduleRepository;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {

    private final ScheduleRepository repository;

    public ScheduleCommandServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Schedule handle(CreateScheduleCommand command) {
        Schedule schedule = new Schedule(command);
        return repository.save(schedule);
    }
}
