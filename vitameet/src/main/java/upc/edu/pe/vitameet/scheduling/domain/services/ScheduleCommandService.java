package upc.edu.pe.vitameet.scheduling.domain.services;

import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;
import upc.edu.pe.vitameet.scheduling.domain.model.commands.CreateScheduleCommand;

public interface ScheduleCommandService {
    Schedule handle(CreateScheduleCommand command);
}
