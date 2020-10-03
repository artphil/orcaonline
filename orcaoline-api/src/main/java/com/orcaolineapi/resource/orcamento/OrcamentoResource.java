package com.orcaolineapi.resource.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.OrcamentoService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoResource extends AbstractResource<Orcamento> {

	private @Autowired OrcamentoRepository repository;

	private @Autowired OrcamentoService service;

	@Override
	public OrcamentoRepository getRepository() {
		return repository;
	}

	@Override
	public OrcamentoService getService() {
		return service;
	}
}
