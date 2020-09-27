package com.orcaolineapi.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.usuario.UsuarioRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class UsuarioService extends AbstractService<Usuario> {

	private @Autowired UsuarioRepository repository;

	@Override
	public UsuarioRepository getRepository() {
		return this.repository;
	}

}
