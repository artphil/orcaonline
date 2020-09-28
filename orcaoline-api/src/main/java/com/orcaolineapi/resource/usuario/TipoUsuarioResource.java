package com.orcaolineapi.resource.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.usuario.PermisaoTipoUsuarioEdicao;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.AbstractService;
import com.orcaolineapi.service.usuario.TipoUsuarioService;

@RestController
@RequestMapping("/tiposUsuarios")
public class TipoUsuarioResource extends AbstractResource<TipoUsuario> {

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

	@GetMapping("permissoesTipoUsuarioEdicao")
	public List<PermisaoTipoUsuarioEdicao> getPermissoesTipoUsuarioEdicao(FilterTipoUsuarioPermissao filtro) {
		List<PermisaoTipoUsuarioEdicao> result = service.getPermissaoTipoUsuarioEdicao(filtro);
		return result;
	}

	@GetMapping("changePermissao")
	public List<PermisaoTipoUsuarioEdicao> changePermissao(FilterTipoUsuarioPermissao filtro) {
		List<PermisaoTipoUsuarioEdicao> result = service.changePermissao(filtro);
		return result;
	}

}
