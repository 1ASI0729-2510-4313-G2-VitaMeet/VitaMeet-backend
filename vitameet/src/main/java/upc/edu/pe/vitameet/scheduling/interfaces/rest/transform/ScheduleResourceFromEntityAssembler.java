package upc.edu.pe.vitameet.scheduling.interfaces.rest.transform;

import upc.edu.pe.vitameet.scheduling.domain.model.aggregates.Schedule;
import upc.edu.pe.vitameet.scheduling.interfaces.rest.resource.ScheduleResource;

public class ScheduleResourceFromEntityAssembler {
    public static ScheduleResource toResource(Schedule entity) {
        return new ScheduleResource(
                entity.getId(),
                entity.getDoctorId(),
                entity.getAvailableFrom().toString(),
                entity.getAvailableTo().toString()
        );
    }
}
