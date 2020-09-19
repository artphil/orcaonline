package com.orcaolineapi.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.repository.usuario.PermissaoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class PermissaoService extends AbstractService<Permissao>{

	private @Autowired PermissaoRepository repository;

	@Override
	public PermissaoRepository getRepository() {
		return this.repository;
	}
}
