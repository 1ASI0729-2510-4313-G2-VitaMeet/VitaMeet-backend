package com.vitameet.api.usermanagement.domain.repositories;

import com.vitameet.api.usermanagement.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    void delete(User user);

    boolean existsById(Long id);
}
