package com.orcaolineapi.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class TipoUsuarioService  extends AbstractService<TipoUsuario>{

	private @Autowired TipoUsuarioRepository repository;

	@Override
	public TipoUsuarioRepository getRepository() {
		return this.repository;
	}
	
}
