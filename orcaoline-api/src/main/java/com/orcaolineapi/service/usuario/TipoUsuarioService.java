package com.orcaolineapi.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.usuario.PermisaoTipoUsuarioEdicao;
import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.repository.usuario.PermissaoRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.resource.usuario.FilterTipoUsuarioPermissao;
import com.orcaolineapi.service.AbstractService;

@Service
public class TipoUsuarioService  extends AbstractService<TipoUsuario>{

	private @Autowired TipoUsuarioRepository repository;
	
	private @Autowired PermissaoRepository permissaoRepository;

	@Override
	public TipoUsuarioRepository getRepository() {
		return this.repository;
	}
	
	public List<PermisaoTipoUsuarioEdicao> getPermissaoTipoUsuarioEdicao(FilterTipoUsuarioPermissao filtro){
		List<Permissao> permissoes =  filtro.getModulo() == null ? permissaoRepository.findAll() : permissaoRepository.findByModulo(filtro.getModulo());
		List<Permissao> permissoesTipoUsuario = repository.findById(filtro.getIdTipoUsuario()).get().getPermissoes();
		List<PermisaoTipoUsuarioEdicao> result = new ArrayList<>();
		for(Permissao p : permissoes) {
			PermisaoTipoUsuarioEdicao pu = new PermisaoTipoUsuarioEdicao(p.getId(), p.getNome(), p.getDescricao(), p.getModulo(), false);
			if(permissoesTipoUsuario.contains(p)) {
				pu.setVinculado(true);
			}
			result.add(pu);
		}
		return result;
	}
	
	public List<PermisaoTipoUsuarioEdicao> changePermissao(FilterTipoUsuarioPermissao filtro) {
		TipoUsuario tipo = repository.findById(filtro.getIdTipoUsuario()).get();
		Permissao permissao = permissaoRepository.findById(filtro.getIdPermissao()).get();
		if(tipo.getPermissoes().contains(permissao)) {
			tipo.getPermissoes().remove(permissao);
		}
		else {
			tipo.getPermissoes().add(permissao);
		}
		repository.save(tipo);
		return getPermissaoTipoUsuarioEdicao(filtro);
	}
	
}
