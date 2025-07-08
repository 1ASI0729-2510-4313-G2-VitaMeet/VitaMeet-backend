package upc.edu.pe.vitameet_backend.api.repository;

import upc.edu.pe.vitameet_backend.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
