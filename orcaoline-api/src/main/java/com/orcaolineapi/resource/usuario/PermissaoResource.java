package com.orcaolineapi.resource.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.sistema.Modulo;
import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.repository.usuario.PermissaoRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.usuario.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource extends AbstractResource<Permissao> {

	private @Autowired PermissaoRepository repository;

	private @Autowired PermissaoService service;

	@Override
	public PermissaoRepository getRepository() {
		return repository;
	}

	@Override
	public PermissaoService getService() {
		return service;
	}

	@CrossOrigin
	@GetMapping
	public List<Permissao> getRecursos() {
		List<Permissao> result = getRepository().findAll();
		result.sort((a, b) -> a.getModulo().getDescricao().compareToIgnoreCase(b.getModulo().getDescricao()));
		return result;
	}

	@CrossOrigin
	@GetMapping("modulos")
	public Modulo[] getModulos() {
		return Modulo.values();
	}
}
