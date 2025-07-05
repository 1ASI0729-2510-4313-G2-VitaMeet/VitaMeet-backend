package com.vitameet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vitameet.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
