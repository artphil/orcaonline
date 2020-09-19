package com.orcaolineapi.resource.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.AbstractService;
import com.orcaolineapi.service.usuario.TipoUsuarioService;

@RestController
@RequestMapping("/tiposUsuarios")
public class TipoUsuarioResource extends AbstractResource<TipoUsuario>{

	private @Autowired TipoUsuarioRepository repository;
	
	private @Autowired TipoUsuarioService service;
	
	@Override
	public AbstractRepository<TipoUsuario, Long> getRepository() {
		return repository;
	}

	@Override
	public AbstractService<TipoUsuario> getService() {
		return service;
	}
}
