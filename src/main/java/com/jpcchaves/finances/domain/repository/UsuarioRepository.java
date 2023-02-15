package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     List<Usuario> findByIdEmail(String email);
}
