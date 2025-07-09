package com.vitameet.api.usermanagement.application.queries;

import com.vitameet.api.shared.domain.Query;

public record GetPatientByDniQuery(String dni) implements Query {
}
