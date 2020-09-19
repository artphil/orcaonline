package com.orcaolineapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcaolineapi.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
}
