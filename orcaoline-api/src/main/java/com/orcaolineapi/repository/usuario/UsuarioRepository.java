package com.orcaolineapi.repository.usuario;

import java.util.Optional;

import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.AbstractRepository;

public interface UsuarioRepository extends AbstractRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
}
