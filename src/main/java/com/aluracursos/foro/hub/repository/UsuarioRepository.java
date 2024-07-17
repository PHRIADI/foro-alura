package com.aluracursos.foro.hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.foro_hub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername (String username);

}
