package com.orcaolineapi.resource.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.AbstractService;
import com.orcaolineapi.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource extends AbstractResource<Usuario> {

	private @Autowired UsuarioRepository repository;
	
	private @Autowired UsuarioService service;
	
	@Override
	public AbstractRepository<Usuario, Long> getRepository() {
		return repository;
	}

	@Override
	public AbstractService<Usuario> getService() {
		return service;
	}
}
