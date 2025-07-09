package com.vitameet.api.usermanagement.application.queries;

import com.vitameet.api.shared.domain.Query;

public record GetPatientByIdQuery(Long patientId) implements Query {
}
