package com.infobank.rest.rest.repository;

import java.util.Optional;

import com.infobank.rest.rest.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
