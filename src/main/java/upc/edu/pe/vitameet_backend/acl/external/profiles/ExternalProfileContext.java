package upc.edu.pe.vitameet_backend.acl.external.profiles;

import org.springframework.stereotype.Service;

@Service
public class ExternalProfileContext {

    public String getUserRole(Long userId) {
        if (userId == 1L) return "DOCTOR";
        if (userId == 2L) return "PATIENT";
        return "GUEST";
    }
}
