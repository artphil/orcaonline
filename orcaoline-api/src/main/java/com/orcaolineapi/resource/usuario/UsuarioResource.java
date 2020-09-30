package com.orcaolineapi.resource.usuario;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.event.RecursoCriadoEvent;
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

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario resource, HttpServletResponse response) {
		Usuario resourceSave = service.save(resource);
		getPublisher().publishEvent(new RecursoCriadoEvent(this, response, resourceSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(resourceSave);
	}
	
	@Override
	public AbstractRepository<Usuario, Long> getRepository() {
		return repository;
	}

	@Override
	public AbstractService<Usuario> getService() {
		return service;
	}
}
